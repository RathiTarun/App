package com.example.splitSavvy.UI

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.splitSavvy.R
import com.example.splitSavvy.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPasswordToggle()
        setupLogin()
        setupRegistrationRedirect()
    }

    // ---------------- PASSWORD TOGGLE ----------------
    private fun setupPasswordToggle() {
        binding.ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                binding.etPassword.inputType = InputType.TYPE_CLASS_TEXT
                binding.ivTogglePassword.setImageResource(R.drawable.ic_eye_off)
            } else {
                binding.etPassword.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.ivTogglePassword.setImageResource(R.drawable.ic_eye)
            }

            binding.etPassword.setSelection(binding.etPassword.text.length)
        }
    }

    // ---------------- LOGIN FLOW ----------------
    private fun setupLogin() {
        binding.btnLogin.setOnClickListener {

            clearInputError()

            val identifier = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (identifier.isEmpty() || password.isEmpty()) {
                toast("Please enter username/email & password")
                showInputError()
                return@setOnClickListener
            }

            performLogin(identifier, password)
        }
    }

    // ---------------- API READY METHOD ----------------
    private fun performLogin(identifier: String, password: String) {

        // 🔌 BACKEND HOOK (replace this whole block later)
        if (mockLogin(identifier, password)) {
            toast("Login Successful")

            // TODO(API): Navigate after real token is received
            // startActivity(Intent(this, HomeActivity::class.java))
            // finish()

        } else {
            toast("Invalid username/email or password")
            shakeViews(binding.EmailContainer, binding.PasswordContainer)
            showInputError()
        }

        // 🔌 REAL API WILL GO HERE LATER
        /*
        authApi.login(LoginRequest(identifier, password))
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(...) { }
                override fun onFailure(...) { }
            })
        */
    }

    // ---------------- MOCK LOGIN ----------------
    private fun mockLogin(identifier: String, password: String): Boolean {
        return (
                (identifier == "test@gmail.com" || identifier == "testuser")
                        && password == "test"
                )
    }

    // ---------------- UI HELPERS ----------------
    private fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun shakeViews(vararg views: View) {
        val shake = AnimationUtils.loadAnimation(this, R.anim.shake)
        views.forEach { it.startAnimation(shake) }
    }

    private fun showInputError() {
        binding.EmailContainer.setBackgroundResource(R.drawable.bg_input_error)
        binding.PasswordContainer.setBackgroundResource(R.drawable.bg_input_error)
    }

    private fun clearInputError() {
        binding.EmailContainer.setBackgroundResource(R.drawable.bg_input)
        binding.PasswordContainer.setBackgroundResource(R.drawable.bg_input)
    }

    // ---------------- SIGN UP REDIRECT ----------------
    private fun setupRegistrationRedirect() {
        val fullText = "New User ? Sign Up"
        val spannable = SpannableString(fullText)

        val startIndex = fullText.indexOf("Sign Up")
        val endIndex = startIndex + "Sign Up".length

        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.newUserText.text = spannable
        binding.newUserText.setOnClickListener {
            startActivity(Intent(this, RegisterStep1Activity::class.java))
        }
    }
}
