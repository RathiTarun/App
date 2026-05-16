package com.example.splitSavvy.model

data class FriendModel(

    val id: Int,
    val name: String,

    val username: String,
    val email: String,

    val avatar: Int,

    var isSelected: Boolean = false
)

