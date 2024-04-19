package com.tinhtx.customapplication.di.component

import android.app.Application
import com.tinhtx.customapplication.ui.activity.ActivityModule
import com.tinhtx.customapplication.di.module.AppModule
import com.tinhtx.customapplication.CustomApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityModule::class
])

interface AppComponent : AndroidInjector<CustomApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: CustomApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: Application)
}