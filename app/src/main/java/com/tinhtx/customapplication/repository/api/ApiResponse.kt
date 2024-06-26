package com.tinhtx.customapplication.repository.api

import com.google.gson.annotations.SerializedName

open class ApiResponse : Response {

    @SerializedName("response")
    val body: ResponseBody? = null

    data class ResponseBody(
        @SerializedName("status")
        val code: Int,
        @SerializedName("info")
        val message: String
    )
}