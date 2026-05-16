package com.example.splitSavvy.data.mock

import com.example.splitSavvy.R
import com.example.splitSavvy.model.FriendModel

object MockFriendData {

    fun getFriends(): List<FriendModel> {

        return listOf(

            FriendModel(
                id=1,
                name = "Sarah Connor",
                username="@sarah_c",
                email = "sarah@gmail.com",
                avatar = R.drawable.ic_avatar

            ),
            FriendModel(
                id=2,
                name="Mike Tyson",
                username="@iron_mike",
                email="mike@gmail.com",
                avatar=R.drawable.ic_avatar
            ),
            FriendModel(
                id = 3,
                name = "Emma Watson",
                username = "@emma_w",
                email = "emma@gmail.com",
                avatar = R.drawable.ic_avatar
            ),

            FriendModel(
                id = 4,
                name = "John Wick",
                username = "@john_w",
                email = "john@gmail.com",
                avatar = R.drawable.ic_avatar
            )
        )
    }
}