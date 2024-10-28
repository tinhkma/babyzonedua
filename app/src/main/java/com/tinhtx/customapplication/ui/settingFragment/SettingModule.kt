package com.tinhtx.customapplication.ui.settingFragment

import androidx.lifecycle.ViewModel
import com.tinhtx.customapplication.di.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class SettingModule {

    @ContributesAndroidInjector
    abstract fun contributeSettingFragment(): SettingFragment

    @Binds
    @IntoMap
    @ViewModelKey(SettingViewModel::class)
    abstract fun bindSettingViewModel(viewModel: SettingViewModel): ViewModel
}