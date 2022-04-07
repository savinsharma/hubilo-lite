package com.hubilo.lite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hubilo.lite.apipack.*
import com.hubilo.lite.databinding.ActivityMainPrjtBinding
import com.hubilo.lite.databinding.ActivitySignupBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainPrjtBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainPrjtBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main_prjt)
    }
}