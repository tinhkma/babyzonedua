package com.tinhtx.customapplication.ui.welcomeScreen

import android.content.Intent
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseActivity
import com.tinhtx.customapplication.databinding.ActivityLoginBinding
import com.tinhtx.customapplication.databinding.ActivityWelcomeBinding
import com.tinhtx.customapplication.ui.activity.MainActivity
import com.tinhtx.customapplication.ui.loginScreen.LoginActivity

class WelcomeActivity : BaseActivity<ActivityWelcomeBinding, WelcomeViewModel>() {

    override val viewModelClass: Class<WelcomeViewModel> = WelcomeViewModel::class.java

    override val layoutRes: Int = R.layout.activity_welcome

    override fun onDataBound(binding: ActivityWelcomeBinding) {
        binding.viewModel = viewModel

        binding.btnNextMain.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    fun clearFocus() {
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