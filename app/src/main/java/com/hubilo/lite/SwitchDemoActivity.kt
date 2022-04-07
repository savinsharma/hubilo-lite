package com.hubilo.lite

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hubilo.lite.apipack.*
import com.hubilo.lite.databinding.ActivityLoginBinding
import com.hubilo.lite.databinding.ActivitySwitchDemoBinding

class SwitchDemoActivity :AppCompatActivity() {

    private lateinit var binding : ActivitySwitchDemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySwitchDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignin.setOnClickListener {
            val loginActivity =
                Intent(applicationContext, LoginActivity::class.java)
            startActivity(loginActivity)
            finish()
        }

        binding.btnWithoutSignin.setOnClickListener {
            makeApis()
        }

    }

    private fun makeApis(){
        if(InternetReachability.hasConnection(this)) {
            val currentMilli = System.currentTimeMillis()
            val email = "${currentMilli}@hubilo.com"
            val firstName = "Anonymous"
            val lastName = "User"
            val password = "$currentMilli"
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
                                    Toast.makeText(this@SwitchDemoActivity, "Something is wrong with login", Toast.LENGTH_LONG).show()
                                }
                            }
                        }
                    }
                }
            })
        } else {
            Toast.makeText(this@SwitchDemoActivity, "Internet connection error", Toast.LENGTH_LONG).show()
        }
    }

}