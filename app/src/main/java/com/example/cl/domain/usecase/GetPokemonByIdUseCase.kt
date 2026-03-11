package com.example.cl.domain.usecase

import com.example.cl.domain.model.Pokemon
import com.example.cl.domain.repository.PokemonRepository

class GetPokemonByIdUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(id: Int): Pokemon = repository.getPokemonById(id)
}
