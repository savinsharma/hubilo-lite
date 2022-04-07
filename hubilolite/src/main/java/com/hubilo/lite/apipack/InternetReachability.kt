package com.hubilo.lite.apipack

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import com.hubilo.lite.R

object InternetReachability {
    fun hasConnection(context: Context?): Boolean {
        return if (context != null) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        } else {
            false
        }
    }

    fun showConnectionErrorMessage(context: Context) {
        Toast.makeText(
            context,
            context.resources.getString(R.string.internet_err),
            Toast.LENGTH_SHORT
        ).show()
    }
}