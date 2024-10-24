package com.tinhtx.customapplication.di.component

import android.app.Application
import com.tinhtx.customapplication.di.activity.ActivityModule
import com.tinhtx.customapplication.di.module.AppModule
import com.tinhtx.customapplication.CustomApplication
import com.tinhtx.customapplication.ui.introScreen.IntroModule
import com.tinhtx.customapplication.ui.loginScreen.LoginModule
import com.tinhtx.customapplication.ui.welcomeScreen.WelcomeModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class, AppModule::class, ActivityModule::class]
)

interface AppComponent : AndroidInjector<CustomApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: CustomApplication): Builder

        fun build(): AppComponent
    }

    fun inject(app: Application)
}