package com.tinhtx.customapplication.ui.introScreen

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseActivity
import com.tinhtx.customapplication.base.showBackArrow
import com.tinhtx.customapplication.databinding.ActivityIntroBinding
import com.tinhtx.customapplication.databinding.ActivityMainBinding
import com.tinhtx.customapplication.ui.loginScreen.LoginActivity

class IntroActivity : BaseActivity<ActivityIntroBinding, IntroViewModel>() {

    override val viewModelClass: Class<IntroViewModel> = IntroViewModel::class.java

    override val layoutRes: Int = R.layout.activity_intro

    override fun onDataBound(binding: ActivityIntroBinding) {
        binding.viewModel = viewModel

        binding.btnNextLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
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