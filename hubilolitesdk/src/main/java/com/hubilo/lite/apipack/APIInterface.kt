package com.hubilo.lite.apipack

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface APIInterface {

    // Check Email Api
    @POST("users/check-email")
    fun checkEmail(
        @Body userRequest: Request<UserRequest>
    ): Call<CommonResponse<LoginResponse>>

    // Login Api
    @POST("users/login")
    fun login(
        @Body userRequest: Request<UserRequest>
    ): Call<CommonResponse<LoginResponse>>

}