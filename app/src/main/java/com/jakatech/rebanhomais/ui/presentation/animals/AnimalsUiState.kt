package com.jakatech.rebanhomais.ui.presentation.animals

import com.jakatech.rebanhomais.kmp.domain.entity.Sexo
import com.jakatech.rebanhomais.kmp.domain.entity.StatusAnimal
import com.jakatech.rebanhomais.kmp.domain.entity.TipoAnimal

data class AnimalsUiState(
    val name: String = "",
    val type: TipoAnimal = TipoAnimal.NOVILHA,
    val breed: String = "Nelore",
    val sex: Sexo = Sexo.FEMEA,
    val birthDate: String = "2023-08-15",
    val weight: String = "",
    val batch: String = "01",
    val status: StatusAnimal = StatusAnimal.ATIVO,
    val isLoading: Boolean = false,
    val successMessage: String? = null,
    val errorMessage: String? = null
)
