package com.jakatech.rebanhomais.ui.presentation.dashboard

data class DashboardUiState(
    val totalAnimals: Int = 124,
    val activeBatches: Int = 6,
    val recentActivity: String = "Animal 'Estrela' added to Lote 01",
    val isLoading: Boolean = false
)
