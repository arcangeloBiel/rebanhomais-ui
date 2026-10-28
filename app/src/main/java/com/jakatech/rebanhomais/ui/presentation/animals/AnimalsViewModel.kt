package com.jakatech.rebanhomais.ui.presentation.animals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakatech.rebanhomais.kmp.data.datasource.FirebaseAnimalRepository
import com.jakatech.rebanhomais.kmp.domain.entity.Animal
import com.jakatech.rebanhomais.kmp.domain.entity.LocalDate
import com.jakatech.rebanhomais.kmp.domain.entity.Sexo
import com.jakatech.rebanhomais.kmp.domain.entity.StatusAnimal
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
            is AnimalsUiEvent.TypeChanged -> {
                val inferredSex = when (event.type.name.uppercase()) {
                    "BEZERRO", "NOVILHO", "BOI", "TOURO" -> Sexo.MACHO
                    else -> Sexo.FEMEA
                }
                _uiState.update { it.copy(type = event.type, sex = inferredSex) }
            }
            is AnimalsUiEvent.BreedChanged -> _uiState.update { it.copy(breed = event.breed) }
            is AnimalsUiEvent.SexChanged -> _uiState.update { it.copy(sex = event.sex) }
            is AnimalsUiEvent.BirthDateChanged -> _uiState.update { it.copy(birthDate = event.birthDate) }
            is AnimalsUiEvent.WeightChanged -> _uiState.update { it.copy(weight = event.weight) }
            is AnimalsUiEvent.BatchChanged -> _uiState.update { it.copy(batch = event.batch) }
            is AnimalsUiEvent.StatusChanged -> _uiState.update { it.copy(status = event.status) }
            AnimalsUiEvent.SubmitAnimal -> registerAnimal()
            AnimalsUiEvent.ClearMessage -> _uiState.update { it.copy(successMessage = null, errorMessage = null) }
        }
    }

    private fun registerAnimal() {
        val state = _uiState.value
        if (state.name.isBlank()) {
            _uiState.update { it.copy(errorMessage = "A identificação do animal não pode ser vazia") }
            return
        }

        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true, errorMessage = null) }
                val animal = Animal(
                    id = "",
                    nome = state.name,
                    tipo = state.type,
                    raca = state.breed.ifBlank { "Nelore" },
                    dataNascimento = parseLocalDate(state.birthDate),
                    sexo = state.sex,
                    pesoAtual = state.weight.toDoubleOrNull() ?: 0.0,
                    status = StatusAnimal.ATIVO, // Always ATIVO by default on new creation
                    lote = state.batch.ifBlank { "01" }
                )
                repository.addAnimal(animal)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        name = "",
                        weight = "",
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

    private fun parseLocalDate(dateStr: String): LocalDate {
        val parts = dateStr.trim().split("-")
        if (parts.size == 3) {
            val year = parts[0].toIntOrNull() ?: 2023
            val month = parts[1].toIntOrNull() ?: 8
            val day = parts[2].toIntOrNull() ?: 15
            return LocalDate(year, month, day)
        }
        return LocalDate(2023, 8, 15)
    }
}
