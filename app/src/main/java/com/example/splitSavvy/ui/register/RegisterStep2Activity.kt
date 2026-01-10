package com.example.splitSavvy.ui.register


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.splitSavvy.R
import com.example.splitSavvy.model.RegistrationDraft
import com.example.splitSavvy.ui.OtpVerificationActivity
import com.google.gson.Gson
import java.io.File


class RegisterStep2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_step2)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val btnSend = findViewById<Button>(R.id.btnSendCode)
        val phoneInput = findViewById<EditText>(R.id.etPhone)

        val countryCode = "+91"

        // 🔴 FAIL FAST if draft missing
        val draft = intent.getSerializableExtra("registration_draft")
                as? RegistrationDraft
            ?: error("Registration draft missing")

        btnBack.setOnClickListener { finish() }

        btnSend.setOnClickListener {

            val phone = phoneInput.text.toString().trim()

            if (phone.length != 10 || !phone.all { it.isDigit() }) {
                phoneInput.error = "Enter valid 10-digit number"
                return@setOnClickListener
            }

            draft.phone = "$countryCode$phone"

            // 🔍 FINAL VERIFICATION (THIS IS WHAT YOU WANTED)
            require(draft.email.isNotBlank())
            require(draft.password.isNotBlank())
            require(draft.phone.startsWith("+91"))

            // 🔥 BACKEND-READY PAYLOAD
            logDraft(draft)

            Toast.makeText(
                this,
                "OTP sent to ${draft.phone}",
                Toast.LENGTH_SHORT
            ).show()

            dumpDraftToExternal(this, draft)


            // Next step later
            val intent = Intent(this, OtpVerificationActivity::class.java)
            startActivity(intent)
        }
    }

    private fun logDraft(draft: RegistrationDraft) {
        // Debug only
        Log.d("REGISTER_PAYLOAD", draft.toString())
    }

    fun dumpDraftToExternal(context: Context, draft: RegistrationDraft) {

        Log.d("DUMP_CHECK", "dumpDraftToExternal() CALLED")

        val file = File(
            context.getExternalFilesDir(null),
            "registration_debug.json"
        )

        val json = Gson().toJson(draft)
        file.writeText(json)

        Log.d("DUMP_CHECK", "FILE WRITTEN")
        Log.d("DUMP_CHECK", "PATH = ${file.absolutePath}")
        Log.d("DUMP_CHECK", "CONTENT = $json")
    }


}
