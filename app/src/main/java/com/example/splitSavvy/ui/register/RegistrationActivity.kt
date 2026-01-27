package com.example.splitSavvy.ui.register

import android.os.Bundle
import com.example.splitSavvy.data.remote.api.ApiClient
import android.os.PersistableBundle
import android.text.InputType
import com.example.splitSavvy.R
import androidx.appcompat.app.AppCompatActivity
import com.example.splitSavvy.core.ui.toast
import com.example.splitSavvy.data.remote.dto.RegisterRequest
import com.example.splitSavvy.data.remote.dto.RegisterResponse
import com.example.splitSavvy.databinding.ActivityRegistrationBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPasswordToggle()
        setupRegister()
        setupBack()
        setupLoginRedirect()
    }

    private fun setupBack(){
        binding.btnBack.setOnClickListener{
            finish()
        }
    }

    private fun setupLoginRedirect(){
        binding.tvLoginRedirect.setOnClickListener{
            finish()
        }
    }

    private fun setupPasswordToggle(){
        binding.btnTogglePassword.setOnClickListener{
            isPasswordVisible = !isPasswordVisible

            binding.etPassword.inputType = if(isPasswordVisible) InputType.TYPE_CLASS_TEXT
            else InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            binding.btnTogglePassword.setImageResource(
                if(isPasswordVisible) R.drawable.ic_eye_off
                else R.drawable.ic_eye
            )

            binding.etPassword.setSelection(binding.etPassword.text.length)
        }
    }

    private fun setupRegister() {
        binding.btnRegister.setOnClickListener {

            val firstName = binding.etFirstName.text.toString().trim()
            val lastName = binding.etLastName.text.toString().trim()
            val userName = binding.etUsername.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (firstName.isEmpty() || lastName.isEmpty() ||
                userName.isEmpty() || email.isEmpty() || password.isEmpty()
            ) {
                toast("Please fill all the fields")
                return@setOnClickListener
            }

            if (!isValidUsername(userName)) {
                toast("Username must be 8–16 chars, start with a letter, no consecutive _ or -")
                return@setOnClickListener
            }

            if (!isValidEmail(email)) {
                toast("Enter a valid email address")
                return@setOnClickListener
            }

            registerUser(firstName, lastName, userName, email, password)
        }
    }


    private fun isValidUsername(username: String): Boolean {
        val regex = Regex("^[A-Za-z](?!.*[_-]{2})[A-Za-z0-9_-]{7,15}$")
        return regex.matches(username)
    }

    private fun isValidEmail(email: String): Boolean {
        val regex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        return regex.matches(email)
    }


    private fun registerUser(
        firstName:String,
        lastName: String,
        userName: String,
        email: String,
        password:String
    ){
        val request = RegisterRequest(
            userName = userName,
            firstName = firstName,
            lastName = lastName,
            Email = email,
            password = password
        )

        ApiClient.api.registerUser(request)
            .enqueue(object : Callback<RegisterResponse> {

                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {

                    if (!response.isSuccessful || response.body() == null) {
                        toast("Server error")
                        return
                    }

                    val result = response.body()!!

                    if (result.status.equals("success", true)) {
                        toast("Account created successfully")
                        finish()
                    } else {
                        toast(result.message ?: "Registration failed")
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    toast("Network error: ${t.message}")
                }
            })
    }
}
