package com.tinhtx.customapplication.di.module

import android.content.Context
import com.tinhtx.customapplication.CustomApplication
import com.tinhtx.customapplication.dao.AppDatabase
import com.tinhtx.customapplication.di.AndroidModule
import com.tinhtx.customapplication.di.api.ApiModule
import dagger.Binds
import dagger.Module

@Suppress("unused")
@Module(includes = [AndroidModule::class, ApiModule::class, AppDatabase::class])
abstract class AppModule {

    @Binds
    abstract fun bindContext(app: CustomApplication): Context
}