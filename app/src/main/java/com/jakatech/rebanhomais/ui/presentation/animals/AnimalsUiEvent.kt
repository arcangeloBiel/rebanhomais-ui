package com.jakatech.rebanhomais.ui.presentation.animals

import com.jakatech.rebanhomais.kmp.domain.entity.Sexo
import com.jakatech.rebanhomais.kmp.domain.entity.StatusAnimal
import com.jakatech.rebanhomais.kmp.domain.entity.TipoAnimal

sealed interface AnimalsUiEvent {
    data class NameChanged(val name: String) : AnimalsUiEvent
    data class TypeChanged(val type: TipoAnimal) : AnimalsUiEvent
    data class BreedChanged(val breed: String) : AnimalsUiEvent
    data class SexChanged(val sex: Sexo) : AnimalsUiEvent
    data class BirthDateChanged(val birthDate: String) : AnimalsUiEvent
    data class WeightChanged(val weight: String) : AnimalsUiEvent
    data class BatchChanged(val batch: String) : AnimalsUiEvent
    data class StatusChanged(val status: StatusAnimal) : AnimalsUiEvent
    data object SubmitAnimal : AnimalsUiEvent
    data object ClearMessage : AnimalsUiEvent
}
