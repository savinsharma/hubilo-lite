package com.hubilo.lite

import androidx.appcompat.app.AppCompatActivity
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.hubilo.lite.apipack.PreferenceKeyConstants
import com.hubilo.lite.apipack.SharedPreferenceUtil
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

        window?.decorView?.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        window.statusBarColor = Color.TRANSPARENT

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed({
            val accessToken = SharedPreferenceUtil.getInstance(this)?.getData(PreferenceKeyConstants.ACCESSTOKEN, "")
            if(accessToken.isNullOrEmpty()){
                val loginActivity = Intent(applicationContext, LoginActivity::class.java)
                startActivity(loginActivity)
            } else {
                val sessionStreamingActivity = Intent(applicationContext, SessionStreamingActivity::class.java)
                startActivity(sessionStreamingActivity)
            }
            finish()
        }, 500)
    }

}