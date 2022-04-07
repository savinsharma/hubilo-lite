package com.hubilo.lite.apipack

import com.google.gson.annotations.SerializedName

data class Error(

    @field:SerializedName("code")
    var code: String? = null,
    
    @field:SerializedName("message")
    var message: String? = null,

    /*@field:SerializedName("errorStack")
    val errorStack: ErrorStack? = null*/
)