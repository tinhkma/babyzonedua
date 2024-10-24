package com.tinhtx.customapplication.ui.historyFragment

import androidx.lifecycle.ViewModel
import com.tinhtx.customapplication.di.viewModel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class HistoryModule {
    @ContributesAndroidInjector
    abstract fun contributeHistoryFragment(): HistoryFragment

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    abstract fun bindHistoryViewModel(viewModel: HistoryViewModel): ViewModel
}