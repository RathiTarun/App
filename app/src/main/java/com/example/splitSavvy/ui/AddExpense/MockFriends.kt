package com.example.splitSavvy.ui.expense

import com.example.splitSavvy.R

object MockFriends {

    fun getFriends(): List<SplitFriend> {

        return listOf(

            SplitFriend(
                "Sarah Jenkins",
                "@sarah_j",
                R.drawable.ic_avatar,
                true
            ),

            SplitFriend(
                "Mike Tyson",
                "@iron_mike",
                R.drawable.ic_avatar,
                true
            ),

            SplitFriend(
                "Emma Watson",
                "@emma_w",
                R.drawable.ic_avatar,
                false
            )
        )
    }
}