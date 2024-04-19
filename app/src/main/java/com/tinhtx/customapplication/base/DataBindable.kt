package com.tinhtx.customapplication.base

import androidx.databinding.ViewDataBinding

interface DataBindable<T : ViewDataBinding> {

    val layoutRes: Int
    val binding: T?

    fun onDataBound(binding: T)
}
