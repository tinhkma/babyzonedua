package com.tinhtx.customapplication.ui.introScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tinhtx.customapplication.di.activity.MainActivityModule
import com.tinhtx.customapplication.di.viewModel.ViewModelFactory
import com.tinhtx.customapplication.di.viewModel.ViewModelKey
import com.tinhtx.customapplication.ui.activity.MainViewModel
import com.tinhtx.customapplication.ui.historyFragment.HistoryModule
import com.tinhtx.customapplication.ui.homeFragment.HomeModule
import com.tinhtx.customapplication.ui.loginScreen.LoginActivity
import com.tinhtx.customapplication.ui.loginScreen.LoginModule
import com.tinhtx.customapplication.ui.settingFragment.SettingModule
import com.tinhtx.customapplication.ui.welcomeScreen.WelcomeActivity
import com.tinhtx.customapplication.ui.welcomeScreen.WelcomeModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class IntroModule {

    @Binds
    @IntoMap
    @ViewModelKey(IntroViewModel::class)
    abstract fun bindIntroViewModel(viewModel: IntroViewModel): ViewModel
}