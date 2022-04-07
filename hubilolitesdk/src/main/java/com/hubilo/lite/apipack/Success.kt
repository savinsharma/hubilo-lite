package com.hubilo.lite.apipack
import com.google.gson.annotations.SerializedName

class Success<T> {

    @SerializedName("message")
    var message: String? = null

    @SerializedName("data")
    var data: T? = null

    @SerializedName("code")
    var code: String? = null
}