package com.example.splitSavvy.data.remote.dto

import org.intellij.lang.annotations.Identifier

data class LoginRequest (
    val identifier: String,
    val password: String
)