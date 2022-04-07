package com.hubilo.lite

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hubilo.lite.apipack.ApiCallResponseCallBack
import com.hubilo.lite.apipack.CommonResponse
import com.hubilo.lite.apipack.LoginHelper
import com.hubilo.lite.apipack.LoginResponse
import com.hubilo.lite.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle = intent.extras
        val email = bundle?.getString("email", "")?:""

        val edtUsername = binding.username
        val edtFirstname = binding.firstname
        val edtLastname = binding.lastname
        val edtPassword = binding.password
        val edtConfirmPassword = binding.confirmPassword
        val btnLogin = binding.login

        edtUsername.setText(email)

        btnLogin.setOnClickListener {
            val firstName = edtFirstname.text.toString().trim()
            val lastName = edtLastname.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val confirmPassword = edtConfirmPassword.text.toString().trim()
            if(firstName.isNullOrEmpty()){
                Toast.makeText(this, "Please enter first name", Toast.LENGTH_LONG).show()
            } else if(lastName.isNullOrEmpty()){
                Toast.makeText(this, "Please enter last name", Toast.LENGTH_LONG).show()
            } else if(password.isNullOrEmpty()){
                Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show()
            } else if(confirmPassword.isNullOrEmpty()){
                Toast.makeText(this, "Please enter confirm password", Toast.LENGTH_LONG).show()
            } else if(confirmPassword != password){
                Toast.makeText(this, "Confirm password should match password", Toast.LENGTH_LONG).show()
            } else {
                //make sign up api
                LoginHelper.signInApi(this, this, email, firstName, lastName, password,  object : ApiCallResponseCallBack {
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
                                        Toast.makeText(this@SignupActivity, "Something is wrong with login", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        }
                    }
                })
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}