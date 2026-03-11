package com.example.cl.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cl.domain.usecase.GetPokemonByIdUseCase
import kotlinx.coroutines.launch
import kotlin.random.Random

class PokemonViewModel(
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase
) : ViewModel() {

    private val _state = MutableLiveData(PokemonUiState())
    val state: LiveData<PokemonUiState> = _state

    fun loadPokemon(id: Int) {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true, errorMessage = null)
            runCatching { getPokemonByIdUseCase(id) }
                .onSuccess { pokemon ->
                    _state.value = PokemonUiState(isLoading = false, pokemon = pokemon)
                }
                .onFailure {
                    _state.value = PokemonUiState(
                        isLoading = false,
                        errorMessage = "Не удалось загрузить покемона"
                    )
                }
        }
    }

    fun loadRandomPokemon() {
        loadPokemon(Random.nextInt(1, 1026))
    }
}
