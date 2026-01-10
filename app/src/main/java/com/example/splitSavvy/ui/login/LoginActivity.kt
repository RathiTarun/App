package com.example.splitSavvy.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import com.example.splitSavvy.R
import com.example.splitSavvy.core.validation.Validator
import com.example.splitSavvy.databinding.ActivityLoginBinding
import com.example.splitSavvy.ui.register.RegisterStep1Activity
import com.example.splitSavvy.core.ui.toast
import com.example.splitSavvy.core.ui.shake


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var inputController: LoginInputController

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputController = LoginInputController(binding)
        inputController.init()

        setupPasswordToggle()
        setupLogin()
        setupRegisterationDirect()
    }

    private fun setupPasswordToggle(){
        binding.ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            binding.etPassword.inputType =
                if(isPasswordVisible) InputType.TYPE_CLASS_TEXT
                else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            binding.ivTogglePassword.setImageResource(
                if(isPasswordVisible)R.drawable.ic_eye_off
                else R.drawable.ic_eye
            )
            binding.etPassword.setSelection(binding.etPassword.text.length)
        }
    }

    private fun setupLogin(){
        binding.btnLogin.setOnClickListener {
            clearInputError()

            val identifier = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if(Validator.isEmpty(identifier,password)){
                toast("Please fill all the fields")
                showInputError()
                return@setOnClickListener
            }

            if(inputController.isPhoneMode() && !Validator.isValidIndianPhone(identifier)){
                toast("Enter valid 10 digit phone number")
                showInputError()
                return@setOnClickListener
            }

            performLogin(identifier, password)
        }
    }

    private fun performLogin(identifier: String, password: String){
        if(mockLogin(identifier,password)){
            toast("Login Successful")
        }
        else{
            toast("Invalid credentials")
            binding.EmailContainer.shake(this)
            binding.PasswordContainer.shake(this)
            showInputError()
        }
    }

    private fun mockLogin(identifier: String, password: String): Boolean{
        return (identifier == "test_user" || identifier=="testuser") && password == "test"
    }

    private fun showInputError(){
        binding.EmailContainer.setBackgroundResource(R.drawable.bg_input_error)
        binding.PasswordContainer.setBackgroundResource(R.drawable.bg_input_error)
    }

    private fun clearInputError(){
        binding.EmailContainer.setBackgroundResource(R.drawable.bg_input)
        binding.PasswordContainer.setBackgroundResource(R.drawable.bg_input)
    }

    private fun setupRegisterationDirect(){
        val text = "New User ? Sign Up"
        val spannable = SpannableString(text)

        val start = text.indexOf("sign Up")
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            start, start + 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.newUserText.text = spannable
        binding.newUserText.setOnClickListener {
            startActivity(Intent(this, RegisterStep1Activity::class.java))
        }
    }
}