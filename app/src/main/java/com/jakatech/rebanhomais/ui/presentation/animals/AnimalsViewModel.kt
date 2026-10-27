package com.jakatech.rebanhomais.ui.presentation.animals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakatech.rebanhomais.kmp.data.datasource.FirebaseAnimalRepository
import com.jakatech.rebanhomais.kmp.domain.entity.Animal
import com.jakatech.rebanhomais.kmp.domain.entity.LocalDate
import com.jakatech.rebanhomais.kmp.domain.entity.Sexo
import com.jakatech.rebanhomais.kmp.domain.entity.StatusAnimal
import com.jakatech.rebanhomais.kmp.domain.entity.TipoAnimal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimalsViewModel : ViewModel() {

    private val repository = FirebaseAnimalRepository()

    private val _uiState = MutableStateFlow(AnimalsUiState())
    val uiState: StateFlow<AnimalsUiState> = _uiState.asStateFlow()

    fun onEvent(event: AnimalsUiEvent) {
        when (event) {
            is AnimalsUiEvent.NameChanged -> _uiState.update { it.copy(name = event.name) }
            is AnimalsUiEvent.BreedChanged -> _uiState.update { it.copy(breed = event.breed) }
            is AnimalsUiEvent.WeightChanged -> _uiState.update { it.copy(weight = event.weight) }
            is AnimalsUiEvent.BatchChanged -> _uiState.update { it.copy(batch = event.batch) }
            AnimalsUiEvent.SubmitAnimal -> registerAnimal()
            AnimalsUiEvent.ClearMessage -> _uiState.update { it.copy(successMessage = null, errorMessage = null) }
        }
    }

    private fun registerAnimal() {
        val state = _uiState.value
        if (state.name.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Animal name cannot be empty") }
            return
        }

        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                val animal = Animal(
                    id = "",
                    nome = state.name,
                    tipo = TipoAnimal.NOVILHA,
                    raca = state.breed.ifBlank { "Nelore" },
                    dataNascimento = LocalDate(2023, 8, 15),
                    sexo = Sexo.FEMEA,
                    pesoAtual = state.weight.toDoubleOrNull() ?: 0.0,
                    status = StatusAnimal.ATIVO,
                    lote = state.batch.ifBlank { "Lote 01" }
                )
                repository.addAnimal(animal)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        name = "",
                        breed = "",
                        weight = "",
                        batch = "",
                        successMessage = "Animal registered successfully!"
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = e.localizedMessage ?: "Failed to register animal"
                    )
                }
            }
        }
    }
}
