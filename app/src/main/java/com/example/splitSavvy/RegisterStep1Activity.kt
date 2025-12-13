package com.example.splitSavvy

import android.media.Image
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class RegisterStep1Activity: AppCompatActivity() {
    private var passwordVisible = false
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_step1)

        val passwordInput=findViewById<EditText>(R.id.inputPassword)
        val togglePassword=findViewById<ImageView>(R.id.btnTogglePassword)
        val backBtn=findViewById<ImageView>(R.id.btnBack)
        val continueBtn = findViewById<Button>(R.id.btnContinue)

        togglePassword.setOnClickListener {
            passwordVisible = !passwordVisible

            if (passwordVisible) {
                passwordInput.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                togglePassword.setImageResource(R.drawable.ic_eye_off)
            } else {
                passwordInput.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                togglePassword.setImageResource(R.drawable.ic_eye)
            }

            passwordInput.setSelection(passwordInput.text.length)
        }

        backBtn.setOnClickListener{
            finish()
        }
    }
}