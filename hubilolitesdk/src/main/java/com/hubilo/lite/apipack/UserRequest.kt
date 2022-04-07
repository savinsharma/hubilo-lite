package com.hubilo.lite.apipack

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @field:SerializedName("email")
    var email: String? = null,

    @field:SerializedName("is_otp_login")
    var isOtpLogin: Boolean? = null,

    @field:SerializedName("mode")
    var mode: String? = null,

    @field:SerializedName("password")
    var password: String? = null,
)
