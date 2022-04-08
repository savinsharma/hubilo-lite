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
            LoginHelper.makeOfflineAPI(this@SwitchDemoActivity, this@SwitchDemoActivity)
            finish()
        }
    }
}