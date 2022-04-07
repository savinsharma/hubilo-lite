package com.hubilo.lite.apipack

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface APIInterface {

    // Check Email Api
    @POST("platformNew/web-state-new-lite")
    fun webStateApi(
        @Body userRequest: Request<UserRequest>
    ): Call<CommonResponse<LoginResponse>>

    // Check Email Api
    @POST("users/check-email")
    fun checkEmail(
        @Body userRequest: Request<UserRequest>
    ): Call<CommonResponse<LoginResponse>>

    // Login Api
    @POST("users/login-lite")
    fun login(
        @Body userRequest: Request<UserRequest>
    ): Call<CommonResponse<LoginResponse>>

    // Signup Api
    @POST("users/signup-lite")
    fun signInApi(
        @Body userRequest: Request<UserRequest>
    ): Call<CommonResponse<LoginResponse>>

    //Session detail after Join session
    @POST("sessions/live-agenda/get")
    fun getSessionDetails(@Body userRequest: Request<UserRequest>): Call<CommonResponse<SessionDetailResponse>>
}