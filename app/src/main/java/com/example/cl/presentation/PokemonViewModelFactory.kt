package com.example.cl.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cl.domain.usecase.GetPokemonByIdUseCase

class PokemonViewModelFactory(
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PokemonViewModel(getPokemonByIdUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
