package com.example.splitSavvy.core.ui

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.splitSavvy.R
    fun Context.toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun View.shake(context: Context) {
        startAnimation(AnimationUtils.loadAnimation(context, R.anim.shake))
    }
