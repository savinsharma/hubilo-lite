package com.hubilo.lite.apipack

import android.content.Context

class AllApiCalls {
    var context: Context = context

    companion object {
        lateinit var allApiCalls:AllApiCalls
    }
    constructor(context: Context){
        if(allApiCalls == null){
            allApiCalls = AllApiCalls(context)
        }
    }
}