package com.tinhtx.customapplication.ui.homeFragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseFragment
import com.tinhtx.customapplication.base.showToast
import com.tinhtx.customapplication.databinding.FragmentHomeBinding
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeInputItem
import com.tinhtx.customapplication.ui.homeFragment.itemView.HomeTitleItem
import com.tinhtx.customapplication.utils.Strings
import com.tinhtx.customapplication.utils.convertDateToString
import com.tinhtx.customapplication.utils.convertToMonthFormat
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), HomeListener {
    override val viewModelClass: Class<HomeViewModel> = HomeViewModel::class.java
    override val layoutRes: Int = R.layout.fragment_home
    private lateinit var recyclerView: RecyclerView
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()
    private lateinit var listener: HomeListener
    private var mYear = 0
    private var mMonth: Int = 0
    private var mDay: Int = 0
    private var mHour: Int = 0
    private var mMinute: Int = 0

    override fun onDataBound(binding: FragmentHomeBinding) {
        binding.let {
            it.viewModel = viewModel
            it.rcvHome.setupRcv()
        }

        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.let {
                it.dataView.observe(this@HomeFragment) {
                    groupAdapter.update(it)
                }
                it.updateData.observe(this@HomeFragment) {
                    if (it.price.isNullOrEmpty() || it.product.isNullOrEmpty() || it.type?.type == "-") {
                        showToast("Fill all text box!!")
                    } else {
                        if (it.date == null) {
                            showToast("Default is current date!")
                            val date = Date()
                            it.date = date.convertDateToString()
                            viewModel.updateDataDailyExpense(it)
                        } else {
                            viewModel.updateDataDailyExpense(it)
                            showToast("Update done data!")
                        }
                    }
                }
                it.onActionDate.observe(this@HomeFragment) {
                    getDatePicker()
                }
            }
        }
    }

    private fun RecyclerView.setupRcv() {
        adapter = groupAdapter.apply {
            setOnItemClickListener { item, _ ->
                //listener.onClickItem()
            }
        }
        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        layoutManager = linearLayoutManager

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
                (groupAdapter.getItem(3) as HomeInputItem).homeDto.title = data
                data.convertToMonthFormat()?.let { viewModel.updateDataDate(it) }
                groupAdapter.notifyDataSetChanged()
            }, mHour, mMinute, false
        )
        timePickerDialog.show()
    }

    override fun onClickItem() {

    }
}