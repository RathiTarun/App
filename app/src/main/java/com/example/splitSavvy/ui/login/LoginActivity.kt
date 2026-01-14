package com.example.splitSavvy.ui.login

import android.content.Intent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
import com.example.splitSavvy.data.remote.api.ApiClient
import com.example.splitSavvy.data.remote.dto.LoginRequest
import com.example.splitSavvy.data.remote.dto.LoginResponse
import com.example.splitSavvy.ui.welcome.WelcomeActivity




class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var inputController: LoginInputController

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputController = LoginInputController(binding)
        inputController.init()

        setupPasswordToggle()
        setupLogin()
        setupRegistrationDirect()
    }

    private fun setupPasswordToggle() {
        binding.ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            binding.etPassword.inputType =
                if (isPasswordVisible) InputType.TYPE_CLASS_TEXT
                else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            binding.ivTogglePassword.setImageResource(
                if (isPasswordVisible) R.drawable.ic_eye_off
                else R.drawable.ic_eye
            )
            binding.etPassword.setSelection(binding.etPassword.text.length)
        }
    }

    private fun setupLogin() {
        binding.btnLogin.setOnClickListener {
            clearInputError()

            val identifier = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (Validator.isEmpty(identifier, password)) {
                toast("Please fill all the fields")
                showInputError()
                return@setOnClickListener
            }

            if (inputController.isPhoneMode() && !Validator.isValidIndianPhone(identifier)) {
                toast("Enter valid 10 digit phone number")
                showInputError()
                return@setOnClickListener
            }

            performLogin(identifier, password)
        }
    }

    private fun performLogin(identifier: String, password: String) {

        val request = LoginRequest(
            identifier = identifier,
            password = password
        )

        ApiClient.api.login(request).enqueue(object: Callback<LoginResponse>{

            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ){
                if(!response.isSuccessful|| response.body()==null){
                    toast("Server error")
                    return
                }

                val result = response.body()!!

                if(result.status != "success"){
                    toast("Invalid username or password")
                    binding.EmailContainer.shake(this@LoginActivity)
                    binding.PasswordContainer.shake(this@LoginActivity)
                    showInputError()
                    return
                }

                openWelcomeScreen(result.firstName)
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable){
                toast("Network error: ${t.message}")
            }
        })
    }




        fun showInputError() {
            binding.EmailContainer.setBackgroundResource(R.drawable.bg_input_error)
            binding.PasswordContainer.setBackgroundResource(R.drawable.bg_input_error)
        }

        fun clearInputError() {
            binding.EmailContainer.setBackgroundResource(R.drawable.bg_input)
            binding.PasswordContainer.setBackgroundResource(R.drawable.bg_input)
        }

        fun setupRegistrationDirect() {
            val text = "New User ? Sign Up"
            val spannable = SpannableString(text)

            val start = text.indexOf("Sign Up")
            spannable.setSpan(
                StyleSpan(Typeface.BOLD),
                start, start + 7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            binding.newUserText.text = spannable
            binding.newUserText.setOnClickListener {
                startActivity(Intent(this, RegisterStep1Activity::class.java))
            }
        }

        fun openWelcomeScreen(firstName: String) {

            val intent = Intent(this, WelcomeActivity::class.java)
            intent.putExtra("FIRST_NAME", firstName)
            startActivity(intent)
            finish()
        }

    }
