package com.example.splitSavvy.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

class LoginResponse (
    @SerializedName("first_name")
    val firstName: String,
    val status: String,
    val username: String
)