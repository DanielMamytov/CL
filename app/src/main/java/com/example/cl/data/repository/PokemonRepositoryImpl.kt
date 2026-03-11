package com.example.cl.data.repository

import com.example.cl.data.api.PokeApiService
import com.example.cl.domain.model.Pokemon
import com.example.cl.domain.repository.PokemonRepository

class PokemonRepositoryImpl(
    private val apiService: PokeApiService
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon> {
        val listResponse = apiService.getPokemonList(limit = limit, offset = offset)

        return listResponse.results.mapIndexed { index, item ->
            val id = extractPokemonId(item.url) ?: (offset + index + 1)
            Pokemon(
                name = item.name,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
            )
        }
    }

    private fun extractPokemonId(url: String): Int? {
        return url.trimEnd('/').substringAfterLast('/').toIntOrNull()
    }
}
