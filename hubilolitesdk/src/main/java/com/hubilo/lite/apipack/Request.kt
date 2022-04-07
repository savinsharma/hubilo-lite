package com.hubilo.lite.apipack

import com.google.gson.annotations.SerializedName

data class Request<T>(
	var payload: Payload<T>? = null
)

data class Payload<T>(
	@SerializedName("data")
	var data: T? = null
)