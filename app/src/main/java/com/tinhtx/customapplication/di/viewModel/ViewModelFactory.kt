package com.tinhtx.customapplication.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return (creators[modelClass]?.get() as? T)
            ?: creators
                .filterKeys { modelClass.isAssignableFrom(it) }
                .mapNotNull { it.value as? T }
                .firstOrNull()
            ?: throw IllegalArgumentException("unknown model class $modelClass")
    }
}