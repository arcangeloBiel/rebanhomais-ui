package com.jakatech.rebanhomais.ui.presentation.login

sealed interface LoginUiEvent {
    data class EmailChanged(val email: String) : LoginUiEvent
    data class PasswordChanged(val password: String) : LoginUiEvent
    data object TogglePasswordVisibility : LoginUiEvent
    data object LoginClicked : LoginUiEvent
    data object ClearError : LoginUiEvent
}
