package com.example.splitSavvy.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.view.KeyEvent
import com.example.splitSavvy.R


class OtpVerificationActivity: AppCompatActivity() {
    private lateinit var otpFields:List<EditText>

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verification)

        otpFields = listOf(
            findViewById(R.id.otp1),
            findViewById(R.id.otp2),
            findViewById(R.id.otp3),
            findViewById(R.id.otp4),
            findViewById(R.id.otp5),
            findViewById(R.id.otp6)
        )
        setupOtpInputs()

        findViewById<ImageView>(R.id.btnBack).setOnClickListener{
            finish()
        }
    findViewById<Button>(R.id.btnVerfiy).setOnClickListener {
        val otp = otpFields.joinToString(""){it.text.toString() }
        if(otp.length<6){
            Toast.makeText(this, "Enter complete OTP", Toast.LENGTH_SHORT).show()
            return@setOnClickListener
        }
        Toast.makeText(this,"OTP verified",Toast.LENGTH_SHORT).show()
    }
        startResendTimer()
    }

    private fun setupOtpInputs() {
        otpFields.forEachIndexed { index, editText ->

            editText.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // do nothing
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    // do nothing
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1 && index < otpFields.size - 1) {
                        otpFields[index + 1].requestFocus()
                    }
                }
            })

            editText.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_DEL && editText.text.isEmpty() && index > 0) {
                    otpFields[index - 1].requestFocus()
                    true
                } else {
                    false
                }
            }
        }
    }


    private fun startResendTimer(){
        val timerText = findViewById<TextView>(R.id.tvTimer)

        object: CountDownTimer(30000,1000){
            override fun onTick(millisUntilFinished: Long) {
                val second= millisUntilFinished/1000
                timerText.text = "Resend code in 00:${if (second < 10) "0$second" else second}"
            }

            override fun onFinish() {
                timerText.text = "You cna resend the code"
            }
        }.start()


    }
}