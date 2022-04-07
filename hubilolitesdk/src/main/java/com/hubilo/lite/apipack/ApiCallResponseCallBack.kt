package com.hubilo.lite.apipack

interface ApiCallResponseCallBack {
    fun onSuccess(mainResponse: CommonResponse<LoginResponse>)
    fun onError(error:String)
}