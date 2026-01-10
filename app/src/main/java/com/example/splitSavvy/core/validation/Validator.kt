package com.example.splitSavvy.core.validation

object Validator {

    fun isEmpty(vararg fields: String): Boolean{
        return fields.any { it.isBlank() }
    }

    fun isValidIndianPhone(phone: String): Boolean{
        return phone.length==10 && phone.all { it.isDigit() }
    }
}