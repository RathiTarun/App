package com.example.splitSavvy.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisterRequest (
    @SerializedName("userName")
    val userName: String,

    @SerializedName("firstName")
    val firstName: String,

    @SerializedName("lastName")
    val lastName: String,

    @SerializedName("Email")
    val Email: String,

    @SerializedName("password")
    val password: String
)
