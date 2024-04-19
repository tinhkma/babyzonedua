package com.tinhtx.customapplication.ui.activity

import com.tinhtx.customapplication.repository.api.ApiManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeApiManager @Inject constructor(
    private val apiManager: ApiManager,
    private val homeApiClient: MainApiClient
) {

}