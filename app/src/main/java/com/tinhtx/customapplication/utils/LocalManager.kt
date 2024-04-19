package com.tinhtx.customapplication.utils

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalManager @Inject constructor() {

    @Inject
    lateinit var preferences: PreferenceManager
}