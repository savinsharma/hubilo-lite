package com.hubilo.lite

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hubilo.lite.apipack.ApiCallResponseCallBack
import com.hubilo.lite.apipack.CommonResponse
import com.hubilo.lite.apipack.LoginHelper
import com.hubilo.lite.apipack.LoginResponse
import com.hubilo.lite.databinding.ActivityLoginBinding

class LoginActivity :AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edtUsername = binding.username
        val edtPassword = binding.password
        val btnLogin = binding.login

        edtUsername.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                edtPassword.visibility = View.GONE
                edtPassword.setText("")
            }
        })

        btnLogin.setOnClickListener {
            val email = edtUsername.text.toString().trim()
            //LoginHelper.validation(edtPassword, applicationContext, true)
            if(edtPassword.visibility == View.GONE) {
                //check email api
                val token =
                    "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7fSwiZXZlbnQiOnsib3JnYW5pc2VyX2lkIjo2NjU5NCwiZXZlbnRfaWQiOjExNTA1LCJkZXZpY2VfdHlwZSI6IldFQiIsImFwaV9rZXkiOiJkODVmYWIyODAyOGQ0MDdlYTc5NTBkNzA0YTQ4NjUwOCIsImxhbmd1YWdlX21ldGFfaWQiOjB9fQ.nxCIISI4DEsxyEDBEpli0k45-a-gLfkTnHHw9zqhLzs"
                LoginHelper.checkLogin(this, this, email, token, object : ApiCallResponseCallBack {
                    override fun onError(error: String) {

                    }

                    override fun onSuccess(mainResponse: CommonResponse<LoginResponse>) {
                        if (mainResponse.status == true) {
                            if (mainResponse.success?.data != null) {
                                if (mainResponse.success?.data is LoginResponse) {
                                    val loginResponse = mainResponse.success?.data as LoginResponse
                                    if (loginResponse.is_register) {
                                        edtPassword.visibility = View.VISIBLE
                                    } else {
                                        //open sign up page here
                                        val signupActivity = Intent(applicationContext, SignupActivity::class.java)
                                        signupActivity.putExtra("email", email)
                                        startActivity(signupActivity)
                                        finish()
                                    }
                                }
                            }
                        }
                    }
                })
            } else {
                //login api
                LoginHelper.loginApi(this, this, email, edtPassword.text.toString(),  object : ApiCallResponseCallBack {
                    override fun onError(error: String) {

                    }

                    override fun onSuccess(mainResponse: CommonResponse<LoginResponse>) {
                        if (mainResponse.status == true) {
                            if (mainResponse.success?.data != null) {
                                if (mainResponse.success?.data is LoginResponse) {
                                    val loginResponse = mainResponse.success?.data as LoginResponse
                                    if (!loginResponse.accessToken.isNullOrEmpty()) {
                                        val sessionStreamingActivity = Intent(applicationContext, SessionStreamingActivity::class.java)
                                        startActivity(sessionStreamingActivity)
                                        finish()
                                    } else {
                                        //open sign up page here
                                        Toast.makeText(this@LoginActivity, "Something is wrong with login", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        }
                    }
                })
            }
        }
    }
}