package com.example.cl.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cl.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase
) : ViewModel() {

    private val _state = MutableLiveData(PokemonUiState())
    val state: LiveData<PokemonUiState> = _state

    fun loadPokemons(limit: Int = 30, offset: Int = 0) {
        viewModelScope.launch {
            _state.value = _state.value?.copy(isLoading = true, errorMessage = null)
            runCatching { getPokemonListUseCase(limit, offset) }
                .onSuccess { pokemons ->
                    _state.value = PokemonUiState(isLoading = false, pokemons = pokemons)
                }
                .onFailure {
                    _state.value = PokemonUiState(
                        isLoading = false,
                        errorMessage = "Не удалось загрузить список покемонов"
                    )
                }
        }
    }
}
