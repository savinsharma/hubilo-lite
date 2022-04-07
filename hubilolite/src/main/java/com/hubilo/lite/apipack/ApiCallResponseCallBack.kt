package com.hubilo.lite.apipack

interface ApiCallResponseCallBack {
    fun onSuccess(mainResponse: CommonResponse<Any>)
    fun onError(error:String)
}