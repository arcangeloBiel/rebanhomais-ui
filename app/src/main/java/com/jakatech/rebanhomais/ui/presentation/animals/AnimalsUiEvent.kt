package com.jakatech.rebanhomais.ui.presentation.animals

sealed interface AnimalsUiEvent {
    data class NameChanged(val name: String) : AnimalsUiEvent
    data class BreedChanged(val breed: String) : AnimalsUiEvent
    data class WeightChanged(val weight: String) : AnimalsUiEvent
    data class BatchChanged(val batch: String) : AnimalsUiEvent
    data object SubmitAnimal : AnimalsUiEvent
    data object ClearMessage : AnimalsUiEvent
}
