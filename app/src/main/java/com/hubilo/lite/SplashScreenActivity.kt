package com.hubilo.lite

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.Window
import android.widget.Toast
import com.hubilo.lite.apipack.*
import com.hubilo.lite.databinding.ActivitySplashScreenBinding

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            if(InternetReachability.hasConnection(this)) {
                if(SharedPreferenceUtil.getInstance(this@SplashScreenActivity)
                        ?.getData(
                            PreferenceKeyConstants.IS_LOGGEDIN,
                            false
                        ) == true){
                    val sessionStreamingActivity = Intent(applicationContext, SessionStreamingActivity::class.java)
                    startActivity(sessionStreamingActivity)
                    finish()
                finish()} else {
                    LoginHelper.webStateApi(this, this, "12231", object : ApiCallResponseCallBack {
                        override fun onError(error: String) {

                        }

                        override fun onSuccess(mainResponse: CommonResponse<LoginResponse>) {
                            if (mainResponse.status == true) {
                                if (mainResponse.success?.data != null) {
                                    if (mainResponse.success?.data is LoginResponse) {
                                        val loginResponse = mainResponse.success?.data as LoginResponse
                                        if (!loginResponse.organiserId.isNullOrEmpty()) {
                                            SharedPreferenceUtil.getInstance(this@SplashScreenActivity)
                                                ?.saveData(
                                                    PreferenceKeyConstants.ORGANISERID,
                                                    loginResponse.organiserId
                                                )
                                        }
                                        if (!loginResponse.access_token.isNullOrEmpty()) {
                                            SharedPreferenceUtil.getInstance(this@SplashScreenActivity)
                                                ?.saveData(
                                                    PreferenceKeyConstants.ACCESSTOKEN,
                                                    loginResponse.access_token
                                                )
                                            val switchDemoActivity =
                                                Intent(applicationContext, SwitchDemoActivity::class.java)
                                            startActivity(switchDemoActivity)
                                            finish()
                                        }
                                    }
                                }
                            }
                        }
                    })
                }
            } else {
                Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show()
            }
        }, 500)
    }

}