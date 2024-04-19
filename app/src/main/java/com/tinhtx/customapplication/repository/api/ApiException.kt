package com.tinhtx.customapplication.repository.api

open class ApiException(
    code: Int,
    message: String
) : RuntimeException("code: $code, message: $message")