package com.example.cl.domain.repository

import com.example.cl.domain.model.Pokemon

interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): List<Pokemon>
}
