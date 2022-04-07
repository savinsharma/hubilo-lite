package com.hubilo.lite

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.ColorUtils
import com.hubilo.lite.apipack.*
import com.hubilo.lite.databinding.LayoutSessionBinding

class SessionStreamingActivity : AppCompatActivity() {

    private lateinit var binding: LayoutSessionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_session)
        //binding = DataBindingUtil.setContentView(this, R.layout.layout_login)
        val isDark = ColorUtils.calculateLuminance(window.statusBarColor) < 0.5
        val decorView = this.window.decorView
        var systemUiVisibilityFlags = decorView.systemUiVisibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            systemUiVisibilityFlags = if (isDark) {
                systemUiVisibilityFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            } else {
                systemUiVisibilityFlags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }
        decorView.systemUiVisibility = systemUiVisibilityFlags
        sessionDetailApiCall()
    }

    fun sessionDetailApiCall(){
        val sessionId = SharedPreferenceUtil.getInstance(this)?.getData(PreferenceKeyConstants.AGENDA_ID, "")?:""
        if(sessionId.isNotEmpty()){
            LoginHelper.sessionDetail(this, this, sessionId, object : SessionApiCallResponseCallBack {
                override fun onError(error: String) {

                }

                override fun onSuccess(mainResponse: CommonResponse<SessionDetailResponse>) {
                    if (mainResponse.status == true) {
                        if (mainResponse.success?.data != null) {
                            if (mainResponse.success?.data is SessionDetailResponse) {
                                val sessionResponse = mainResponse.success?.data as SessionDetailResponse

                            }
                        }
                    }
                }
            })
        }
    }
}