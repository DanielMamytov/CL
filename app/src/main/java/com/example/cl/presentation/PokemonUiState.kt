package com.example.cl.presentation

import com.example.cl.domain.model.Pokemon

data class PokemonUiState(
    val isLoading: Boolean = false,
    val pokemon: Pokemon? = null,
    val errorMessage: String? = null
)
