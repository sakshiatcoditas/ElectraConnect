package com.example.electraconnect

import android.util.Patterns

class ValidationUtils {

    fun validateEmail(email: String):String?{
        return when {
            email.isBlank()->"Email cannot be empty"
            !Patterns.EMAIL_ADDRESS.matcher(email).matches()->"Invalid email format"
            else-> null
        }
    }

    fun validatePassword(password: String):String?{
        val passwordRegex="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+\$).{8,20}\$".toRegex()

        return when{
            password.isBlank()->"Password cannot be empty"
            !passwordRegex.matches(password)->"Invalid Password format"
            else->null
        }
    }
}