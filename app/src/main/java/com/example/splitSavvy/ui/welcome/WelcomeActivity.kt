package com.example.splitSavvy.ui.welcome

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.splitSavvy.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        val name = intent.getStringExtra("FIRST_NAME") ?: "User"

        val formatted = name.lowercase().replaceFirstChar { it.uppercase() }

        findViewById<TextView>(R.id.tvWelcomeUser).text =
            "Welcome, $formatted 👋"
    }
}
