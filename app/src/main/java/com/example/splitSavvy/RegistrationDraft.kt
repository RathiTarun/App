
package com.example.splitSavvy

import java.io.Serializable

data class RegistrationDraft(
    var name: String="",
    var email: String = "",
    var password: String = "",
    var phone: String = ""
) : Serializable
