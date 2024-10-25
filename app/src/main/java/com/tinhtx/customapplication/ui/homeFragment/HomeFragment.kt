package com.tinhtx.customapplication.ui.homeFragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseFragment
import com.tinhtx.customapplication.base.showToast
import com.tinhtx.customapplication.dao.entities.DailyExpense
import com.tinhtx.customapplication.dao.entities.ExpenseType
import com.tinhtx.customapplication.dao.entities.User
import com.tinhtx.customapplication.databinding.FragmentHomeBinding
import com.tinhtx.customapplication.utils.Strings
import com.tinhtx.customapplication.utils.convertDateToString
import com.tinhtx.customapplication.utils.convertToMonthFormat
import com.tinhtx.customapplication.utils.formatValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), HomeListener {
    override val viewModelClass: Class<HomeViewModel> = HomeViewModel::class.java
    override val layoutRes: Int = R.layout.fragment_home
    private var mYear = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0
    private var mHour: Int = 0
    private var mMinute: Int = 0
    private var mType: ExpenseType? = null

    override fun onDataBound(binding: FragmentHomeBinding) {
        binding.let {
            it.viewModel = viewModel
            it.dateView.setOnClickListener {
                getDatePicker()
            }
            it.btnButton.setOnClickListener {
                val productInfo = binding.edtTitle.text.toString()
                val priceInfo = binding.edtPrice.text.toString()
                val dateInfo = binding.edtDate.text.toString()

                if (productInfo.isEmpty() || priceInfo.isEmpty() || mType == null) {
                    showToast("Fill all text box!!")
                } else {
                    val data = DailyExpense(
                        type = mType,
                        product = productInfo.trim(),
                        price = priceInfo.trim(),
                        date = dateInfo.trim(),
                        location = null
                    )
                    if (dateInfo.isEmpty()) {
                        showToast("Default is current date!")
                        val date = Date()
                        data.date = date.convertDateToString()
                    } else {
                        showToast("Update done data!")
                    }
                    viewModel.updateDataDailyExpense(data)
                    resetDataView()
                }
            }

            it.btnAddType.setOnClickListener {
                val dialogFragment = TypeDialogFragment(viewModel)
                dialogFragment.isCancelable = false
                dialogFragment.show(parentFragmentManager, "TypeDialogFragment")
            }
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.let {
                it.dataType.observe(this@HomeFragment) { dataType ->
                    val listType = dataType.map { it.type }
                    if (listType.isNotEmpty()) {
                        val adapter = ArrayAdapter(
                            this@HomeFragment.requireContext(), R.layout.list_item_dropdown, listType
                        )
                        (binding.typeDropDown as? AutoCompleteTextView)?.setAdapter(adapter)
                        binding.typeDropDown.setOnItemClickListener { _, _, i, _ ->
                            mType = dataType[i]
                        }
                    }
                }

                it.dataUser.observe(this@HomeFragment) { user ->
                    updateDataHeader(user.firstOrNull())
                    viewModel.getDataExpense()
                    viewModel.dataDailyExpense.observe(this@HomeFragment) { dataDailyExpense ->
                        updateDataHeader(user.firstOrNull(), dataDailyExpense)
                    }
                }

                it.dataHeader.observe(this@HomeFragment) {
                    it.second?.let { dataDailyExpense -> updateDataHeader(it.first, dataDailyExpense) }
                }
            }
        }
    }

    private fun updateDataHeader(user: User?, dataDailyExpense: List<DailyExpense> = emptyList()) {
        val nowAvailable = user?.limit ?: Strings.EMPTY
        this@HomeFragment.binding?.let {
            it.titleScreen.text = "Hello, ${user?.firstName}"
        }
        if (nowAvailable.isNotEmpty()) {
            val usedAmount = if (dataDailyExpense.isNotEmpty()) dataDailyExpense.sumOf {
                it.price?.toDouble() ?: 0.0
            } else 0.0
            val available = nowAvailable.toDouble() - usedAmount.toString().toDouble()
            val availableColor = if (available < 0.0) R.color.redColor
            else R.color.colorPrimary
            binding?.availableBalancesAmount?.setTextColor(
                ContextCompat.getColor(
                    requireContext(), availableColor
                )
            )
            this@HomeFragment.binding?.let {
                if (usedAmount.toString().isNotEmpty() && nowAvailable.isNotEmpty()) {
                    it.availableAmount.text = nowAvailable.formatValue()
                    it.usedAmount.text = usedAmount.toString().formatValue()
                    it.availableBalancesAmount.text =
                        (nowAvailable.toDouble() - usedAmount.toString().toDouble()).toString().formatValue()
                }
            }
        }
    }

    private fun resetDataView() {
        binding?.edtTitle?.setText(Strings.EMPTY)
        binding?.edtPrice?.setText(Strings.EMPTY)
        binding?.edtDate?.setText(Strings.EMPTY)
        binding?.typeDropDown?.setText(Strings.EMPTY)
        viewModel.getDataExpense()
        viewModel.getDataUser()
    }

    private fun getDatePicker() {
        val c: Calendar = Calendar.getInstance()
        mYear = c.get(Calendar.YEAR)
        mMonth = c.get(Calendar.MONTH)
        mDay = c.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(), { _, year, monthOfYear, dayOfMonth ->
                val date = dayOfMonth.toString() + "/" + (monthOfYear + 1) + "/" + year
                getTimePicker(date)
            }, mYear, mMonth, mDay
        )
        datePickerDialog.show()
    }

    private fun getTimePicker(date: String) {
        val c = Calendar.getInstance()
        mHour = c[Calendar.HOUR_OF_DAY]
        mMinute = c[Calendar.MINUTE]

        val timePickerDialog = TimePickerDialog(
            requireContext(), { _, hourOfDay, minute ->
                val time = "$hourOfDay:$minute"
                val data = "$date $time"
                data.convertToMonthFormat()?.let {
                    binding?.edtDate?.setText(it)
                }
            }, mHour, mMinute, false
        )
        timePickerDialog.show()
    }

    override fun onClickItem() {
    }
}