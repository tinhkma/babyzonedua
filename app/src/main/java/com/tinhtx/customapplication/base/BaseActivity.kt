package com.tinhtx.customapplication.base

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.tinhtx.customapplication.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding, U : BaseViewModel> : DaggerAppCompatActivity(),
    DataBindable<T> {

    protected abstract val viewModelClass: Class<U>

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected val viewModel: U by lazy { ViewModelProviders.of(this, viewModelFactory).get(viewModelClass) }

    override lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        updateUIStatusBar()
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        DataBindingUtil.setContentView<T>(this, layoutRes)
            .let {
                it.setLifecycleOwner(this)
                binding = it
                onDataBound(it)
            }
    }

    private fun updateUIStatusBar() {
        // made status bar completely transparent but do not impact to navigation bar
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.colorTransparent)

        // made status bar be darked
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}