package com.kuby.kubot.util

import android.util.Log
import com.kuby.kubot.domain.model.ErrorResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ResponseBody

object ConvertErrorBody {

    fun convert(errorBody: ResponseBody?): ErrorResponse? {
        return try {
            errorBody?.source().let {
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val adapter = moshi.adapter(ErrorResponse::class.java)
                adapter.fromJson(it)!!
            }

        } catch (e: Exception) {
            e.message?.let { Log.e("Error", it) }
            null
        }
    }
}