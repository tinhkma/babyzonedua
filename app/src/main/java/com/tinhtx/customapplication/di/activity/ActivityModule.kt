package com.tinhtx.customapplication.di.activity

import androidx.lifecycle.ViewModelProvider
import com.tinhtx.customapplication.di.viewModel.ViewModelFactory
import com.tinhtx.customapplication.ui.activity.MainActivity
import com.tinhtx.customapplication.ui.historyFragment.HistoryModule
import com.tinhtx.customapplication.ui.homeFragment.HomeModule
import com.tinhtx.customapplication.ui.introScreen.IntroActivity
import com.tinhtx.customapplication.ui.introScreen.IntroModule
import com.tinhtx.customapplication.ui.loginScreen.LoginActivity
import com.tinhtx.customapplication.ui.loginScreen.LoginModule
import com.tinhtx.customapplication.ui.settingFragment.SettingModule
import com.tinhtx.customapplication.ui.welcomeScreen.WelcomeActivity
import com.tinhtx.customapplication.ui.welcomeScreen.WelcomeModule
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    internal abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [IntroModule::class])
    internal abstract fun contributeIntroActivity(): IntroActivity

    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [WelcomeModule::class])
    internal abstract fun contributeWelcomeActivity(): WelcomeActivity
}