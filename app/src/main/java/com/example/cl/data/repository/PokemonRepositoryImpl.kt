package com.example.cl.data.repository

import com.example.cl.data.api.PokeApiService
import com.example.cl.domain.model.Pokemon
import com.example.cl.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val apiService: PokeApiService
) : PokemonRepository {

    override suspend fun getPokemonById(id: Int): Pokemon {
        val response = apiService.getPokemonById(id)
        return Pokemon(
            name = response.name,
            imageUrl = response.sprites.frontDefault.orEmpty()
        )
    }
}
