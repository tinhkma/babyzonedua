package com.tinhtx.customapplication.ui.welcomeScreen

import androidx.lifecycle.ViewModel
import com.tinhtx.customapplication.di.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class WelcomeModule {

    @Binds
    @IntoMap
    @ViewModelKey(WelcomeViewModel::class)
    abstract fun bindWelcomeViewModel(viewModel: WelcomeViewModel): ViewModel
}