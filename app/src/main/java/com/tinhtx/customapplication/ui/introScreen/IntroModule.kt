package com.tinhtx.customapplication.ui.introScreen

import androidx.lifecycle.ViewModel
import com.tinhtx.customapplication.di.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class IntroModule {

    @Binds
    @IntoMap
    @ViewModelKey(IntroViewModel::class)
    abstract fun bindIntroViewModel(viewModel: IntroViewModel): ViewModel
}