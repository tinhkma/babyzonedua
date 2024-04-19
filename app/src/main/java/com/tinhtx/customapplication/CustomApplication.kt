package com.tinhtx.customapplication

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDex
import com.tinhtx.customapplication.utils.AppLogger
import com.tinhtx.customapplication.utils.LocalManager
import com.tinhtx.customapplication.utils.PreferenceManager
import com.tinhtx.customapplication.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

@SuppressLint("CheckResult")
class CustomApplication : DaggerApplication() {

    @Inject
    lateinit var preferenceManager: PreferenceManager

    @Inject
    lateinit var localManager: LocalManager

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        AppLogger.init()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}