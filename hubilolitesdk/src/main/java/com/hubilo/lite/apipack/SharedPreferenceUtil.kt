package com.hubilo.lite.apipack

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.hubilo.lite.R

class SharedPreferenceUtil {

    private var appSharedPrefs: SharedPreferences? = null
    private var sharedPreferenceUtil: SharedPreferenceUtil? = null

    companion object {

        @Volatile
        private var INSTANCE: SharedPreferenceUtil? = null

        fun getInstance(context: Context?): SharedPreferenceUtil? {
            if (INSTANCE == null) {
                INSTANCE = SharedPreferenceUtil()
                INSTANCE?.appSharedPrefs = context?.getSharedPreferences(
                    PreferenceKeyConstants.PREF_NAME + context.getString(R.string.app_name),
                    AppCompatActivity.MODE_PRIVATE
                )
            }
            return INSTANCE
        }
    }

    @Synchronized
    fun saveData(key: String, value: String?): Boolean {
        return if (appSharedPrefs != null) {
            val editor = appSharedPrefs?.edit()
            if (value != null) {
                //val valueEnc = AES.encrypt(value, Store.encryptKey)
                editor?.putString(key, value)
                editor?.commit() ?: false
            } else {
                false
            }
        } else {
            false
        }
    }

    @Synchronized
    fun saveData(key: String, value: Boolean): Boolean {
        return if (appSharedPrefs != null) {
            val editor = appSharedPrefs?.edit()
            editor?.putBoolean(key, value)
            editor?.commit() ?: false
        } else {
            false
        }
    }

    @Synchronized
    fun saveData(key: String, value: Long): Boolean {
        return if (appSharedPrefs != null) {
            val editor = appSharedPrefs?.edit()
            editor?.putLong(key, value)
            editor?.commit() ?: false
        } else {
            false
        }
    }


    @Synchronized
    fun saveData(key: String, value: Float): Boolean {
        return if (appSharedPrefs != null) {
            val editor = appSharedPrefs?.edit()
            editor?.putFloat(key, value)
            editor?.commit() ?: false
        } else {
            false
        }
    }

    @Synchronized
    fun saveData(key: String, value: Int): Boolean {
        return if (appSharedPrefs != null) {
            val editor = appSharedPrefs?.edit()
            editor?.putInt(key, value)
            editor?.commit() ?: false
        } else {
            false
        }
    }

    @Synchronized
    fun removeData(key: String): Boolean {
        return if (appSharedPrefs != null) {
            val editor = appSharedPrefs?.edit()
            editor?.remove(key)
            editor?.commit() ?: false
        } else {
            false
        }
    }

    @Synchronized
    fun getData(key: String, defaultValue: Boolean): Boolean {
        return if (appSharedPrefs != null) {
            appSharedPrefs?.getBoolean(key, defaultValue) ?: false
        } else {
            defaultValue
        }
    }

    @Synchronized
    fun getData(key: String, defaultValue: String): String {
        return if (appSharedPrefs != null) {
            val strValue = appSharedPrefs?.getString(key, defaultValue)
            /*if (strValue != null && strValue.isNotEmpty()) {
                strValue = AES.decrypt(strValue, Store.encryptKey)
            }*/
            strValue ?: defaultValue
        } else {
            defaultValue
        }
    }

    @Synchronized
    fun getData(key: String, defaultValue: Float): Float {
        return if (appSharedPrefs != null) {
            appSharedPrefs?.getFloat(key, defaultValue) ?: 0.0f
        } else {
            defaultValue
        }
    }

    @Synchronized
    fun getData(key: String, defaultValue: Int): Int {
        return if (appSharedPrefs != null) {
            appSharedPrefs?.getInt(key, defaultValue) ?: 0
        } else {
            defaultValue
        }
    }

    @Synchronized
    fun getData(key: String, defaultValue: Long): Long {
        return if (appSharedPrefs != null) {
            appSharedPrefs?.getLong(key, defaultValue) ?: 0
        } else {
            defaultValue
        }
    }

   /* @Synchronized
    fun deleteAllData() {
        val editor = appSharedPrefs?.edit()
        editor?.clear()
        sharedPreferenceUtil = null
        editor?.apply()
    }

    @Synchronized
    fun clearUserData() {
        val fcmToken = getData(PreferenceKeyConstants.PREF_DEVICE_TOKEN, "")
        deleteAllData()
        saveData(PreferenceKeyConstants.PREF_DEVICE_TOKEN, fcmToken)
    }*/


//    fun deleteAllData() {
//        val editor = xebiaSharedPrefs!!.edit()
//        val prefs = xebiaSharedPrefs!!.all
//        for (prefToReset in prefs.entries) {
//            if(prefToReset.key != SharedPreferenceUtil.PREF_LANGUAGE_CODE){
//                editor.remove(prefToReset.key).commit()
//                sharedPreferenceUtil = null
//            }
//
//        }
//
////        editor!!.clear()
//        editor!!.commit()
//    }
}