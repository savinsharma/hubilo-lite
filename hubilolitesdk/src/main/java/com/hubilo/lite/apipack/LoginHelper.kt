package com.hubilo.lite.apipack

import android.app.Activity
import android.content.Context

object LoginHelper {

    fun checkLogin(activity: Activity, context: Context, email:String, token:String){
        SharedPreferenceUtil.getInstance(context)?.saveData(PreferenceKeyConstants.ACCESSTOKEN, token)

        val loginUserRequest = UserRequest()
        loginUserRequest.email = email
        loginUserRequest.isOtpLogin = false

        val payload = Payload(loginUserRequest)
        val request = Request(payload)
        val apiCalls= AllApiCalls.singleInstance(context)

        apiCalls?.mainResponseApiCall(activity, request, object : ApiCallResponseCallBack{
            override fun onError(error: String) {

            }

            override fun onSuccess(mainResponse: CommonResponse<Any>) {

            }
        })
        println("Help me")
    }
}