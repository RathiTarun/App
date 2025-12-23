package com.example.splitSavvy


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class RegisterStep2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_step2)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnSend = findViewById<Button>(R.id.btnSendCode)
        val phoneInput = findViewById<EditText>(R.id.etPhone)

        // Simple country codes (expand later or replace with library)
        val countryCode = "+91"

        btnBack.setOnClickListener {
            finish()
        }

        btnSend.setOnClickListener {
            val phone = phoneInput.text.toString().trim()

            // Basic validation for Indian phone numbers
            if (phone.length != 10 || !phone.all { it.isDigit() }) {
                phoneInput.error = "Enter a valid 10-digit mobile number"
                return@setOnClickListener
            }

            // Final phone number with country code
            val fullPhoneNumber = "$countryCode$phone"

            // TODO: Send OTP using Firebase / backend
            Toast.makeText(
                this,
                "OTP sent to $fullPhoneNumber",
                Toast.LENGTH_SHORT
            ).show()

            // Navigate to OTP screen (Step 3)
            // val intent = Intent(this, OtpVerificationActivity::class.java)
            // intent.putExtra("phone_number", fullPhoneNumber)
            // startActivity(intent)
        }
    }
}