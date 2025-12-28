package com.example.splitSavvy

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RegisterStep1Activity: AppCompatActivity() {
    private var passwordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_step1)

        val nameInput= findViewById<EditText>(R.id.InputName)
        val emailInput = findViewById<EditText>(R.id.inputEmail)
        val passwordInput = findViewById<EditText>(R.id.inputPassword)
        val togglePassword = findViewById<ImageView>(R.id.btnTogglePassword)
        val continueBtn = findViewById<Button>(R.id.btnContinue)
        val backBtn = findViewById<ImageView>(R.id.btnBack)

        togglePassword.setOnClickListener {
            passwordVisible = !passwordVisible
            passwordInput.inputType =
                if (passwordVisible)
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                else
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            passwordInput.setSelection(passwordInput.text.length)
        }

        backBtn.setOnClickListener { finish() }

        continueBtn.setOnClickListener {

            val name = nameInput.text.toString().trim()
            val email = emailInput.text.toString().trim()
            val password = passwordInput.text.toString()

            if(name.isBlank()){
                nameInput.error = "Name Required"
                return@setOnClickListener
            }
            if (email.isBlank()) {
                emailInput.error = "Email required"
                return@setOnClickListener
            }

            if (password.length < 6) {
                passwordInput.error = "Password too short"
                return@setOnClickListener
            }

            // ✅ SINGLE SOURCE OF TRUTH
            val draft = RegistrationDraft(
                name = name,
                email = email,
                password = password
            )


            val intent = Intent(this, RegisterStep2Activity::class.java)
                intent.putExtra("registration_draft", draft)
                startActivity(intent)


        }

        loginRedirect()
    }
    private fun loginRedirect() {
        val LoginUser = findViewById<TextView>(R.id.LoginUser)

        val fullText = "Already have an Account? Log in"
        val spannable = SpannableString(fullText)

        val startIndex = fullText.indexOf("Log in")
        val endIndex = startIndex + "Log in".length

        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        LoginUser.text = spannable
        LoginUser.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    }