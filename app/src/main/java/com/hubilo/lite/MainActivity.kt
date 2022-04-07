package com.hubilo.lite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hubilo.lite.apipack.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_prjt)
        val email = "vivek@hubilo.com"
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7fSwiZXZlbnQiOnsib3JnYW5pc2VyX2lkIjo2NjU5NCwiZXZlbnRfaWQiOjExNTA1LCJkZXZpY2VfdHlwZSI6IldFQiIsImFwaV9rZXkiOiJkODVmYWIyODAyOGQ0MDdlYTc5NTBkNzA0YTQ4NjUwOCIsImxhbmd1YWdlX21ldGFfaWQiOjB9fQ.nxCIISI4DEsxyEDBEpli0k45-a-gLfkTnHHw9zqhLzs"
        LoginHelper.checkLogin(this, this, email, token)
    }
}