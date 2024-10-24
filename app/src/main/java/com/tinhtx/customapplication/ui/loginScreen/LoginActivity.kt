package com.tinhtx.customapplication.ui.loginScreen

import android.content.Intent
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseActivity
import com.tinhtx.customapplication.databinding.ActivityIntroBinding
import com.tinhtx.customapplication.databinding.ActivityLoginBinding
import com.tinhtx.customapplication.ui.welcomeScreen.WelcomeActivity

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    override val viewModelClass: Class<LoginViewModel> = LoginViewModel::class.java

    override val layoutRes: Int = R.layout.activity_login

    override fun onDataBound(binding: ActivityLoginBinding) {
        binding.viewModel = viewModel

        binding.btnNextWelcome.setOnClickListener {
            startActivity(Intent(this, WelcomeActivity::class.java))
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