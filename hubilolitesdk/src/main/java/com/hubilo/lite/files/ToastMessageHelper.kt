package com.hubilo.lite.files

import android.content.Context
import android.widget.Toast

open class ToastMessageHelper {
    fun createToast(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}