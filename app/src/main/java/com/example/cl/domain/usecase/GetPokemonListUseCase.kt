package com.example.cl.domain.usecase

import com.example.cl.domain.model.Pokemon
import com.example.cl.domain.repository.PokemonRepository

class GetPokemonListUseCase(
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int = 30, offset: Int = 0): List<Pokemon> {
        return repository.getPokemonList(limit = limit, offset = offset)
    }
}
