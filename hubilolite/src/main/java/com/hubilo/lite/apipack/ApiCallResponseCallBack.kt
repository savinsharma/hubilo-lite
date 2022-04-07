package com.hubilo.lite.apipack

interface ApiCallResponseCallBack {
    fun onSuccess(mainResponse: CommonResponse)
    fun onError(error:String)
}