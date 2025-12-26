package com.example.splitSavvy

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
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

    private fun setupLogin() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                toast("Please enter email & password")
                return@setOnClickListener
            }

            if (isValidUser(email, password)) {
                toast("Login Succesful")
            } else {
                toast("Invalid email or password")
                shakeViews(binding.etEmail, binding.etPassword)
                showInputError()


            }
        }
    }

    private fun isValidUser(email: String, password: String): Boolean {
        return email == "test@gmail.com" && password == "test"
    }

    private fun toast(msg: String) {
        android.widget.Toast.makeText(this, msg, android.widget.Toast.LENGTH_SHORT).show()
    }

    private fun shakeViews(vararg views: android.view.View) {
        val shake = android.view.animation.AnimationUtils.loadAnimation(this, R.anim.shake)

        views.forEach { it.startAnimation(shake) }
    }

    private fun showInputError() {
        binding.EmailContainer.setBackgroundResource(R.drawable.bg_input_error)
        binding.PasswordContainer.setBackgroundResource(R.drawable.bg_input_error)
    }

    private fun clearInputError() {
        binding.etEmail.setBackgroundResource(R.drawable.bg_input)
        binding.etPassword.setBackgroundResource(R.drawable.bg_input)
    }

    private fun setupRegistrationRedirect() {
        val fullText = "New User ? Sign Up"
        val spannable = android.text.SpannableString(fullText)

        //Finding where Sign Up starts
        val startIndex = fullText.indexOf("Sign Up")
        val endIndex = startIndex + "Sign Up".length

        //make sign UP Bold
        spannable.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            startIndex, endIndex, android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.newUserText.text = spannable
        binding.newUserText.setOnClickListener {
            val intent = Intent(this, RegisterStep1Activity::class.java)
            startActivity(intent)
        }
    }
}
