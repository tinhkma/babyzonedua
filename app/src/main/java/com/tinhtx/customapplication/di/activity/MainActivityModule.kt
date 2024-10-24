package com.tinhtx.customapplication.di.activity

import androidx.lifecycle.ViewModel
import com.tinhtx.customapplication.di.viewModel.ViewModelKey
import com.tinhtx.customapplication.ui.activity.MainViewModel
import com.tinhtx.customapplication.ui.historyFragment.HistoryModule
import com.tinhtx.customapplication.ui.homeFragment.HomeModule
import com.tinhtx.customapplication.ui.settingFragment.SettingModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module(
    includes = [HomeModule::class, HistoryModule::class, SettingModule::class]
)
abstract class MainActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}