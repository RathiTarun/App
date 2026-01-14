package com.example.splitSavvy.data.remote.api

import com.example.splitSavvy.data.remote.dto.LoginRequest
import com.example.splitSavvy.data.remote.dto.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call


interface AuthApi{

    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}