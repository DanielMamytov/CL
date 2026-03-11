package com.example.cl.presentation

import com.example.cl.domain.model.Pokemon

data class PokemonUiState(
    val isLoading: Boolean = false,
    val pokemons: List<Pokemon> = emptyList(),
    val errorMessage: String? = null
)
