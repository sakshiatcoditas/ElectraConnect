package com.example.electraconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.electraconnect.UserPreferences
import com.example.electraconnect.presentation.login.FirstLoginState
import com.example.electraconnect.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirstLoginViewModel @Inject constructor(
    private val repo: LoginRepository,
    private val prefs: UserPreferences
) : ViewModel() {

    var uiState by mutableStateOf(FirstLoginState())
        private set

    fun preload(email: String, tempPassword: String) {
        uiState = uiState.copy(email = email, tempPassword = tempPassword)
    }

    fun updateNewPassword(value: String) {
        uiState = uiState.copy(newPassword = value)
    }

    fun updateConfirmPassword(value: String) {
        uiState = uiState.copy(confirmPassword = value)
    }

    fun resetPassword(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                uiState = uiState.copy(isLoading = true)

                val res = repo.firstLoginSetPassword(
                    email = uiState.email,
                    temp = uiState.tempPassword,
                    newPass = uiState.newPassword,
                    confirm = uiState.confirmPassword
                )

                prefs.saveTokens(res.access_token, res.refresh_token)

                onSuccess()

            } catch (e: Exception) {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Something went wrong"
                )
            }
        }
    }
}
