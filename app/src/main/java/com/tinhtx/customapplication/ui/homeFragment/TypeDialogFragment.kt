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

class TypeDialogFragment(val homeViewModel: HomeViewModel) : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_add_type, container, false)
        view.findViewById<TextView>(R.id.btn_ok).setOnClickListener {
            val type = view.findViewById<TextView>(R.id.edt_type).text.toString()
            if (type.isEmpty()) showToast("Fill all text box!!")
            else {
                showToast("Save type done!")
                homeViewModel.insertType(type)
            }
            dismiss()
        }
        view.findViewById<TextView>(R.id.btn_cancel).setOnClickListener {
            dismiss()
        }
        return view
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