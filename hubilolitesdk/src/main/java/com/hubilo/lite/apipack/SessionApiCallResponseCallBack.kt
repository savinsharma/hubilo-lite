package com.hubilo.lite.apipack

interface SessionApiCallResponseCallBack {
    fun onSuccess(mainResponse: CommonResponse<SessionDetailResponse>)
    fun onError(error:String)
}