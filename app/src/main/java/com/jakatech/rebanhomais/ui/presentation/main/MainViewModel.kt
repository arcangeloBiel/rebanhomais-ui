package com.jakatech.rebanhomais.ui.presentation.main

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
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    // Instancia o repositório e os casos de uso aqui dentro de forma segura
    private val animalRepository = FirebaseAnimalRepository()

    // Se você tiver UseCases criados no KMP, injete-os ou chame o repositório diretamente:
    // private val addAnimalUseCase = AddAnimalUseCase(animalRepository)
    // private val getAnimaisUseCase = GetAnimaisUseCase(animalRepository)

    private val _uiState = MutableStateFlow<String>("Pronto")
    val uiState: StateFlow<String> = _uiState.asStateFlow()

    fun registrarNovoAnimal() {
        viewModelScope.launch {
            try {
                _uiState.value = "Salvando..."
                val animal = Animal(
                    id = "",
                    nome = "Estrela",
                    tipo = TipoAnimal.NOVILHA,
                    raca = "Nelore",
                    dataNascimento = LocalDate(2023, 8, 15),
                    sexo = Sexo.FEMEA,
                    pesoAtual = 380.0,
                    status = StatusAnimal.ATIVO,
                    lote = "Lote 01"
                )

                // Exemplo chamando direto o repositório ou o use case:
                animalRepository.addAnimal(animal) // Substitua pelo seu método real do KMP

                _uiState.value = "Animal cadastrado com sucesso!"
            } catch (e: Exception) {
                _uiState.value = "Erro ao cadastrar: ${e.localizedMessage}"
            }
        }
    }
}