package com.hubilo.lite.apipack

import com.google.gson.annotations.SerializedName

data class CommonResponse<T>(

    @field:SerializedName("success")
    var success: Success<T>? = null,

    @field:SerializedName("error")
    val error: Error? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)