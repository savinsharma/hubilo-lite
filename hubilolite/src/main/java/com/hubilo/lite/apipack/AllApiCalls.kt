package com.hubilo.lite.apipack

import android.app.Activity
import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllApiCalls(private val context: Context?) {
    /*
    private var stateCallResponseCall: Call<StateCallResponse>? = null
    private val makeStateCallAPIInterface: MakeStateCallAPIInterface?*/
    private var apiInterfaceClass: APIInterface? = null
    private var mainResponseCall: Call<CommonResponse>? = null

    fun mainResponseApiCall(
        activity: Activity?,
        path: String?,
        bodyParameterClass: BodyParameterClass?,
        apiCallData: ApiCallResponseCallBack
    ) {
        if (apiInterfaceClass == null) {
            apiInterfaceClass = APIClient.client?.create(APIInterface::class.java)
        }
        //mainResponseCall = apiInterfaceClass.getSetSpeakerList(path, bodyParameterClass)
        mainResponseCall?.enqueue(object : Callback<CommonResponse?> {
            override fun onResponse(call: Call<CommonResponse?>, response: Response<CommonResponse?>) {
                if (response.body() != null) {
                    apiCallData.onSuccess(response.body()!!)
                } else {
                    apiCallData.onError(" ")
                }
            }

            override fun onFailure(call: Call<CommonResponse?>, t: Throwable) {
                apiCallData.onError(t.message + " ")
            }
        })
    }

   /* fun mainResonseApiCall1(
        activity: Activity?,
        path: String?,
        bodyParameterClass: BodyParameterClass?,
        fields: String?,
        apiCallData: ApiCallResponse
    ) {
        if (apiInterfaceClass == null) {
            apiInterfaceClass = ApiClient.getClient().create(APIInterfaceClass::class.java)
        }
        mainResponseCall = apiInterfaceClass.getSetSpeakerList1(path, bodyParameterClass)
        mainResponseCall.enqueue(object : Callback<MainResponse?>() {
            fun onResponse(call: Call<MainResponse?>?, response: Response<MainResponse?>?) {
                var eventUpdatedLastTimeStateCall: Long = 0
                val generalHelper = GeneralHelper(context)
                if (generalHelper != null && generalHelper.loadPreferences(Utility.EVENT_LAST_UPDATED_AT) != null) {
                    try {
                        if (!generalHelper.loadPreferences(Utility.EVENT_LAST_UPDATED_AT).trim()
                                .equalsIgnoreCase("")
                        ) {
                            eventUpdatedLastTimeStateCall =
                                generalHelper.loadPreferences(Utility.EVENT_LAST_UPDATED_AT)
                                    .toLong()
                        }
                    } catch (e: NumberFormatException) {
                        e.printStackTrace()
                    }
                }
                var eventUpdateLastTimeAPI: Long = 0
                if (response.body() != null) {
                    var flag = ""
                    var title = ""
                    var message = ""
                    var link = ""
                    var buttonName = ""
                    if (response.body().getFlag() != null) {
                        flag = response.body().getFlag().toString() + ""
                    }
                    if (response.body().getTitle() != null) {
                        title = response.body().getTitle().toString() + ""
                    }
                    if (response.body().getMessage() != null) {
                        message = response.body().getMessage().toString() + ""
                    }
                    if (response.body().getLink() != null) {
                        link = response.body().getLink().toString() + ""
                    }
                    if (response.body().getButtonTitle() != null) {
                        buttonName = response.body().getButtonTitle().toString() + ""
                    }
                    generalHelper.flagResponse(context, flag, link, title, message, buttonName)
                    if (response.body().getEventLastUpdatedAt() != null && !response.body()
                            .getEventLastUpdatedAt().trim().equalsIgnoreCase("")
                    ) {
                        eventUpdateLastTimeAPI = response.body().getEventLastUpdatedAt().toLong()
                    }
                    if (eventUpdatedLastTimeStateCall != 0L) {
                        if (eventUpdateLastTimeAPI >= eventUpdatedLastTimeStateCall) {
                            // make state call and update the data
                            if (makeStateCallAPIInterface != null) {
                                makeStateCallAPIInterface.makeStateCallData(true, path)
                            }
                        } else {
                            if (makeStateCallAPIInterface != null) {
                                makeStateCallAPIInterface.makeStateCallData(false, path)
                            }
                        }
                    } else {
                        if (makeStateCallAPIInterface != null) {
                            makeStateCallAPIInterface.makeStateCallData(false, path)
                        }
                    }
                }
                if (response != null && response.body() != null) {
                    apiCallData.onSuccess(response.body())
                } else {
                    apiCallData.onError(" ")
                }
            }

            fun onFailure(call: Call<MainResponse?>?, t: Throwable) {
                if (t is SSLPeerUnverifiedException || t is SSLHandshakeException) {
                    val link =
                        "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID
                    val title = "New update available"
                    val message =
                        "A new version of the App is available on the store. Kindly, update it to use the app seamlessly"
                    val buttonName = "Update Now"
                    generalHelper.openUpdateAppOrLogoutDialog(
                        context,
                        link,
                        "update",
                        R.drawable.app_update_icon,
                        title,
                        message,
                        buttonName,
                        false
                    )
                }
                println("Something with forgot pwd error - " + t.message)
                apiCallData.onError(t.message + " ")
            }
        })
    }*/

    companion object {
        private var apiCalls: AllApiCalls? = null
        fun singleInstance(context: Context?): AllApiCalls? {
            if (apiCalls == null) {
                apiCalls = AllApiCalls(context)
            }
            return apiCalls
        }
    }

    init {
        /*generalHelper = GeneralHelper(context)
        makeStateCallAPIInterface = MainActivityWithSidePanel.makeStateCallAPIInterface*/
    }
}