package com.jakatech.rebanhomais.ui.presentation.animals

data class AnimalsUiState(
    val name: String = "",
    val breed: String = "",
    val weight: String = "",
    val batch: String = "",
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)
