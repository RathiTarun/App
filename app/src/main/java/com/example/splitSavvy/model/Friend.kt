package com.example.splitSavvy.model

data class Friend (
    val id: String,
    val name: String,
    val username: String,
    var isSelected: Boolean = false
)