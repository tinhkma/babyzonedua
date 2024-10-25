package com.tinhtx.customapplication.ui.welcomeScreen

import android.content.Intent
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseActivity
import com.tinhtx.customapplication.databinding.ActivityWelcomeBinding
import com.tinhtx.customapplication.ui.activity.MainActivity
import com.tinhtx.customapplication.utils.LocalManager
import javax.inject.Inject

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding, WelcomeViewModel>() {

    override val viewModelClass: Class<WelcomeViewModel> = WelcomeViewModel::class.java

    override val layoutRes: Int = R.layout.activity_welcome

    @Inject
    lateinit var localManager: LocalManager

    override fun onDataBound(binding: ActivityWelcomeBinding) {
        binding.viewModel = viewModel

        binding.btnNextMain.setOnClickListener {
            updateUser()
            localManager.preferences.setBoolean("KEY_START_APP", true)
        }

        binding.root.setOnClickListener {
            clearFocus()
        }
    }

    private fun updateUser() {
        binding.let {
            val fullName = "${it.edtFirstName.text} ${it.edtLastName.text}"
            viewModel.insertData(fullName)
            if (fullName.isNotEmpty()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun clearFocus() {
        val v = currentFocus
        if (v is EditText) {
            val outRect = Rect()
            v.getGlobalVisibleRect(outRect)
            v.clearFocus()
            val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
        }
    }
}