package com.example.splitSavvy.UI

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.splitSavvy.R

class WelcomeActivity: AppCompatActivity() {

    private lateinit var loginButton: Button
    private lateinit var createAccountButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        loginButton = findViewById(R.id.btnSignIn)
        createAccountButton = findViewById(R.id.btnCreateAccount)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        createAccountButton.setOnClickListener {
            val intent = Intent(this, RegisterStep1Activity::class.java)
            startActivity(intent)


        }

    }
}