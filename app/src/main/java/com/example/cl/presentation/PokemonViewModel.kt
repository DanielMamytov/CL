package com.example.cl.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cl.domain.model.Pokemon
import com.example.cl.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _pokemons = MutableLiveData<List<Pokemon>>(emptyList())
    val pokemons: LiveData<List<Pokemon>> = _pokemons

    fun loadPokemons(limit: Int = 30, offset: Int = 0) {
        viewModelScope.launch {
            runCatching { getPokemonListUseCase(limit, offset) }
                .onSuccess { _pokemons.value = it }
                .onFailure { _pokemons.value = emptyList() }
        }
    }
}
