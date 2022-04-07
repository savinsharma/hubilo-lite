package com.hubilo.hubilolite.files

import android.content.Context
import android.widget.Toast

class ToastMessageHelper {
    fun createToast(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}