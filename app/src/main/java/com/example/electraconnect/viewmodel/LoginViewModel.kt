package com.example.electraconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electraconnect.UserPreferences
import com.example.electraconnect.ValidationUtils
import com.example.electraconnect.presentation.login.LoginState
import com.example.electraconnect.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: LoginRepository,
    private val prefs: UserPreferences,
    private val validator: ValidationUtils
) : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEmailChange(value: String) {
        state = state.copy(email = value, emailError = null)
    }

    fun onPasswordChange(value: String) {
        state = state.copy(password = value, passwordError = null)
    }

    fun login(
        onFirstLogin: (email: String, tempPassword: String) -> Unit,
        onSuccess: () -> Unit
    ) {
        val emailErr = validator.validateEmail(state.email)
        val passErr = validator.validatePassword(state.password)

        if (emailErr != null || passErr != null) {
            state = state.copy(emailError = emailErr, passwordError = passErr)
            return
        }

        viewModelScope.launch {
            try {
                state = state.copy(isLoading = true)

                val response = repo.login(state.email, state.password)

                // First login case
                if (response.need_password_reset == true) {
                    onFirstLogin(state.email, state.password) // temp password = old password
                    return@launch
                }

                // Store tokens for normal login
                prefs.saveTokens(
                    response.access_token!!,
                    response.refresh_token!!
                )

                onSuccess()

            } catch (e: Exception) {
                state = state.copy(isLoading = false)
            } finally {
                state = state.copy(isLoading = false)
            }
        }
    }
}
