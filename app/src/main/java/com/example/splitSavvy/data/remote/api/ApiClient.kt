package com.example.splitSavvy.data.remote.api

import android.util.Log
import com.example.splitSavvy.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val api: AuthApi by lazy {
        Log.d("API_CHECK", "BASE_URL = ${BuildConfig.BASE_URL}")
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)


    }
}
