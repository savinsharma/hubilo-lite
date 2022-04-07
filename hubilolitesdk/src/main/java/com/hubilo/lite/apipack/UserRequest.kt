package com.hubilo.lite.apipack

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @field:SerializedName("firstName")
    var firstName: String? = null,

    @field:SerializedName("lastName")
    var lastName: String? = null,

    @field:SerializedName("email")
    var email: String? = null,

    @field:SerializedName("is_otp_login")
    var isOtpLogin: Boolean? = null,

    @field:SerializedName("mode")
    var mode: String? = null,

    @field:SerializedName("password")
    var password: String? = null,

    @field:SerializedName("source")
    var source: String? = null,

    @field:SerializedName("app_version")
    var app_version: String? = null,

    @field:SerializedName("device_type")
    var device_type: String? = null,

    @field:SerializedName("event_id")
    var event_id: String? = null,

    @field:SerializedName("language")
    var language: Int? = null
)
