package com.tinhtx.customapplication.ui.welcomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tinhtx.customapplication.di.viewModel.ViewModelFactory
import com.tinhtx.customapplication.di.viewModel.ViewModelKey
import com.tinhtx.customapplication.ui.loginScreen.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class WelcomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel::class)
    abstract fun bindWelcomeViewModel(viewModel: WelcomeViewModel): ViewModel
}