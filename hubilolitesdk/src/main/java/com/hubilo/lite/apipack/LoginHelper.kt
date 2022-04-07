package com.hubilo.lite.apipack

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import com.hubilo.lite.R

object LoginHelper {

    fun webStateApi(activity: Activity, context: Context, event_id:String, apiCallResponseCallBack: ApiCallResponseCallBack){

        val loginUserRequest = UserRequest()
        loginUserRequest.source = PreferenceKeyConstants.COMMUNITY
        loginUserRequest.language = 34
        loginUserRequest.app_version = "1.0.0"
        loginUserRequest.device_type = PreferenceKeyConstants.ANDROID
        loginUserRequest.event_id = event_id
        val payload = Payload(loginUserRequest)
        val request = Request(payload)
        val apiCalls= AllApiCalls.singleInstance(context)

        apiCalls?.webStateApi(activity, request, apiCallResponseCallBack)
        println("Help me check email")
    }

    fun checkLogin(activity: Activity, context: Context, email:String, apiCallResponseCallBack: ApiCallResponseCallBack){
        val loginUserRequest = UserRequest()
        loginUserRequest.email = email
        loginUserRequest.isOtpLogin = false

        val payload = Payload(loginUserRequest)
        val request = Request(payload)
        val apiCalls= AllApiCalls.singleInstance(context)

        apiCalls?.checkEmail(activity, request, apiCallResponseCallBack)
        println("Help me check email")
    }

    fun loginApi(activity: Activity, context: Context, email: String, password:String, apiCallResponseCallBack: ApiCallResponseCallBack) {
        val loginUserRequest = UserRequest()
        loginUserRequest.email = email
        loginUserRequest.mode = PreferenceKeyConstants.PASSWORD
        loginUserRequest.password = password.trim()

        val payload = Payload(loginUserRequest)
        val request = Request(payload)
        val apiCalls= AllApiCalls.singleInstance(context)

        apiCalls?.login(activity, request, apiCallResponseCallBack)
        println("Help me login")
    }

    fun signInApi(activity: Activity, context: Context, email: String, firstName:String, lastName:String, password:String, apiCallResponseCallBack: ApiCallResponseCallBack) {
        val loginUserRequest = UserRequest()
        loginUserRequest.firstName = firstName
        loginUserRequest.lastName = lastName
        loginUserRequest.email = email
        loginUserRequest.password = password.trim()

        val payload = Payload(loginUserRequest)
        val request = Request(payload)
        val apiCalls= AllApiCalls.singleInstance(context)

        apiCalls?.signInApi(activity, request, apiCallResponseCallBack)
        println("Help me login")
    }

    fun sessionDetail(activity: Activity, context: Context, agenda_id: String, sessionApiCallResponseCallBack: SessionApiCallResponseCallBack) {
        val loginUserRequest = UserRequest()
        loginUserRequest.agenda_id = agenda_id
        loginUserRequest.is_stream = true

        val payload = Payload(loginUserRequest)
        val request = Request(payload)
        val apiCalls= AllApiCalls.singleInstance(context)

        apiCalls?.sessionId(activity, request, sessionApiCallResponseCallBack)
        println("Help me login")
    }

    /**
     * Helper method to check validation fo email input field
     * [showError] to show error or not
     */
    fun validation(edtEmail: EditText, context: Context, showError: Boolean): Boolean {
        val error: String
        return if (edtEmail.text.toString().trim { it <= ' ' }
                .equals("", ignoreCase = true)) {
            error = context.resources.getString(R.string.INVALID_EMAIL)
            if (showError)
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            false
        } else if (!isValidEmail(edtEmail.text.toString().trim { it <= ' ' })) {
            error = context.resources.getString(R.string.EMAIL_FORMAT_IS_INCORRECT)
            if (showError)
                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            false
        } else {
            true
        }
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}