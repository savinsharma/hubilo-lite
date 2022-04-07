package com.hubilo.lite.apipack

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST


interface APIInterface {

    // Check Email Api
    @POST("users/check-email")
    fun checkEmail(
        @Body userRequest: Request<UserRequest>
    ): Single<CommonResponse<LoginResponse>>

    /*@POST("app/{path}")
    fun getLogin(
        @Path("path") path: String,
        @Body loginRequest: Request<UserRequest>
    ): Single<CommonResponse<LoginResponse>>*/
}