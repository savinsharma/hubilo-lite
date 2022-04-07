package com.hubilo.lite.apipack

import android.content.Context
import retrofit2.Retrofit
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIClient {
    private val BASE_URL = "https://api.v2dev.demohubilo.com/api/v2/"
    private var retrofit: Retrofit? = null
    fun getClient(context: Context): Retrofit? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val client: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(6, TimeUnit.MINUTES)
            .readTimeout(6, TimeUnit.MINUTES)
            .writeTimeout(6, TimeUnit.MINUTES)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("accept", "application/json")
                    .addHeader("Content-Type", "application/json")
                    .addHeader(
                        "language",
                        "34"
                    )
                    .addHeader(
                        "Authorization",
                        SharedPreferenceUtil.getInstance(context)
                            ?.getData(PreferenceKeyConstants.ACCESSTOKEN, "") ?: ""
                    )
                    .build()
                chain.proceed(newRequest)
            }.addInterceptor(interceptor).build()
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        return retrofit
    }
}