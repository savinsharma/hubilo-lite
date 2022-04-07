package com.hubilo.lite.apipack

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.hubilo.lite.R

object LoginHelper {

    fun checkLogin(activity: Activity, context: Context, email:String, token:String, apiCallResponseCallBack: ApiCallResponseCallBack){
        SharedPreferenceUtil.getInstance(context)?.saveData(PreferenceKeyConstants.ACCESSTOKEN, token)

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