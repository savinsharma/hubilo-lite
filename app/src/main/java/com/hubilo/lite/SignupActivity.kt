package com.hubilo.lite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hubilo.lite.databinding.ActivitySignupBinding
import com.hubilo.lite.databinding.ActivitySplashScreenBinding

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            } else {
                //make sign up api
            }
        }
    }
}