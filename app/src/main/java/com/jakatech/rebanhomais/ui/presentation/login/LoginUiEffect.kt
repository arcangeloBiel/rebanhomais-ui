package com.jakatech.rebanhomais.ui.presentation.login

sealed interface LoginUiEffect {
    data object NavigateToMain : LoginUiEffect
    data class ShowSnackbar(val message: String) : LoginUiEffect
}
