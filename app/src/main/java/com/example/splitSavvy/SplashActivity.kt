package com.example.splitSavvy

import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // grab views

        val bigGlow = findViewById<ImageView>(R.id.bigGlow)
        val smallGlow = findViewById<ImageView>(R.id.smallGlow)
        val inner1 = findViewById<ImageView>(R.id.inner1)
        val inner2 = findViewById<ImageView>(R.id.inner2)
        val glassOrb = findViewById<ImageView>(R.id.glassOrb)

        // Apply Blur Effects

        bigGlow.setRenderEffect(
            RenderEffect.createBlurEffect(90f, 90f, Shader.TileMode.CLAMP)
        )

        smallGlow.setRenderEffect(
            RenderEffect.createBlurEffect(70f, 70f, Shader.TileMode.CLAMP)
        )

        inner1.setRenderEffect(
            RenderEffect.createBlurEffect(20f, 20f, Shader.TileMode.CLAMP)
        )

        inner2.setRenderEffect(
            RenderEffect.createBlurEffect(18f, 18f, Shader.TileMode.CLAMP)
        )

        glassOrb.setRenderEffect(
            RenderEffect.createBlurEffect(8f, 8f, Shader.TileMode.CLAMP)
        )


        // Only Big glow pulses
        val slowPulse = AnimationUtils.loadAnimation(this,R.anim.pulse_slow)
        bigGlow.startAnimation(slowPulse)




    }
}