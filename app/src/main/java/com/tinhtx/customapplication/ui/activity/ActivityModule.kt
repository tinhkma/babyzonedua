package com.tinhtx.customapplication.ui.activity

import androidx.lifecycle.ViewModelProvider
import com.tinhtx.customapplication.di.activity.MainActivityModule
import com.tinhtx.customapplication.di.viewModel.ViewModelFactory
import com.tinhtx.customapplication.ui.historyFragment.HistoryModule
import com.tinhtx.customapplication.ui.homeFragment.HomeModule
import com.tinhtx.customapplication.ui.settingFragment.SettingModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module(
    includes = [HomeModule::class, HistoryModule::class, SettingModule::class]
)
abstract class ActivityModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}