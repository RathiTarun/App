package com.example.splitSavvy.ui

import android.content.Intent
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.splitSavvy.R
import com.example.splitSavvy.ui.firstScreen.FirstScreen

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bigGlow = findViewById<ImageView>(R.id.bigGlow)
        val smallGlow = findViewById<ImageView>(R.id.smallGlow)
        val glassOrb = findViewById<ImageView>(R.id.glassOrb)

        // Apply blur only on Android 12+ (API 31+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            bigGlow.setRenderEffect(RenderEffect.createBlurEffect(180f, 180f, Shader.TileMode.CLAMP))
            smallGlow.setRenderEffect(RenderEffect.createBlurEffect(140f, 140f, Shader.TileMode.CLAMP))
            glassOrb.setRenderEffect(RenderEffect.createBlurEffect(12f, 12f, Shader.TileMode.CLAMP))
        }

        // Pulse animation
        val slowPulse = AnimationUtils.loadAnimation(this, R.anim.pulse_glow)
        bigGlow.startAnimation(slowPulse)

        //delay for 2 seconds, then go to LoginActivity

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, FirstScreen::class.java))
            finish()
        }, 2000)
        }

    }
