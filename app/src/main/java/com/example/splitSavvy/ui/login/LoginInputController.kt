package com.example.splitSavvy.ui.login

import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import com.example.splitSavvy.databinding.ActivityLoginBinding

class LoginInputController (private var binding: ActivityLoginBinding){

    fun init(){
        setupSmartIdentifierInput()
    }

    private fun setupSmartIdentifierInput(){
        binding.etEmail.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if(s.isNullOrEmpty()){
                    reset()
                    return
                }

                val firstChar=s[0]

                when{
                    firstChar.isDigit()-> switchToPhoneMode()
                    firstChar.isLetter()-> switchToUsernameMode()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
        })
    }

    private fun switchToPhoneMode(){
        binding.tvIdentifierLabel.text = "Phone No."
        binding.tvCountryCode.visibility= View.VISIBLE
        binding.tvCountryCode.text="+91"
        binding.etEmail.inputType= InputType.TYPE_CLASS_NUMBER
    }

    private fun switchToUsernameMode(){
        binding.tvIdentifierLabel.text="Username"
        binding.tvCountryCode.visibility=View.GONE
        binding.etEmail.inputType= InputType.TYPE_CLASS_TEXT
    }

    private fun reset(){
        binding.tvIdentifierLabel.text="Username or Phone No."
        binding.tvCountryCode.visibility = View.GONE
        binding.etEmail.inputType= InputType.TYPE_CLASS_TEXT
    }

    fun isPhoneMode(): Boolean{
        return binding.tvCountryCode.visibility==View.VISIBLE
    }
}