package com.tinhtx.customapplication.ui.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.showToast
import com.tinhtx.customapplication.databinding.DialogAddTypeBinding
import com.tinhtx.customapplication.model.DialogDto

class TypeDialogFragment(val data: DialogDto) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DialogAddTypeBinding.inflate(inflater, container, false)
        applyView(binding)

        binding.btnOk.setOnClickListener {
            if (binding.edtType.text.toString().isEmpty()) showToast(data.errorText)
            else {
                data.onClickOk?.invoke(binding.edtType.text.toString())
            }
            dismiss()
        }
        binding.btnCancel.setOnClickListener {
            data.onClickCancel?.invoke(binding.edtType.text.toString())
            dismiss()
        }
        return binding.root
    }

    private fun applyView(binding: DialogAddTypeBinding) {
        binding.title.text = data.title ?: ""
        binding.description.apply {
            text = data.description ?: ""
            visibility = if (data.description.isNullOrEmpty()) View.GONE else View.VISIBLE
        }
        binding.btnOk.text = data.titleOk
        binding.btnCancel.text = data.titleCancel
        binding.edtType.apply {
            hint = data.textHint ?: ""
            inputType = data.textType ?: 0
        }
    }

    override fun onResume() {
        super.onResume()
        val window = dialog!!.window ?: return
        val params = window.attributes
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = params
    }
}