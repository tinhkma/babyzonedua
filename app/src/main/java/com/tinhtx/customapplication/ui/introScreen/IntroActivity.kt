package com.tinhtx.customapplication.ui.introScreen

import android.content.Intent
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.tinhtx.customapplication.R
import com.tinhtx.customapplication.base.BaseActivity
import com.tinhtx.customapplication.databinding.ActivityIntroBinding
import com.tinhtx.customapplication.ui.loginScreen.LoginActivity

class IntroActivity : BaseActivity<ActivityIntroBinding, IntroViewModel>() {

    override val viewModelClass: Class<IntroViewModel> = IntroViewModel::class.java

    override val layoutRes: Int = R.layout.activity_intro

    override fun onDataBound(binding: ActivityIntroBinding) {
        binding.viewModel = viewModel

        binding.btnNextLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val imageUrls = listOf(R.drawable.image_template, R.drawable.image_template, R.drawable.image_template)
        val adapter = ImagePagerAdapter(imageUrls)
        viewPager.adapter = adapter
        TabLayoutMediator(binding.intoTabLayout, viewPager) { tab, position -> }.attach()
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