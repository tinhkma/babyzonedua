package com.tinhtx.customapplication.base

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel() : ViewModel(), LifecycleObserver {

    protected val disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
    }

    private fun dispose() {
        disposables.clear()
        disposables.dispose()
    }
}