package com.hubilo.lite.apipack

import android.app.Activity
import android.content.Context

object LoginHelper {

    fun checkLogin(activity: Activity, context: Context, email:String, token:String, apiCallResponseCallBack: ApiCallResponseCallBack){
        SharedPreferenceUtil.getInstance(context)?.saveData(PreferenceKeyConstants.ACCESSTOKEN, token)

        val loginUserRequest = UserRequest()
        loginUserRequest.email = email
        loginUserRequest.isOtpLogin = false

        val payload = Payload(loginUserRequest)
        val request = Request(payload)
        val apiCalls= AllApiCalls.singleInstance(context)

        apiCalls?.mainResponseApiCall(activity, request, apiCallResponseCallBack)
        println("Help me")
    }
}