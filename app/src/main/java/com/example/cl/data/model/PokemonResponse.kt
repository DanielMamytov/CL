package com.example.cl.data.model

data class PokemonListResponse(
    val results: List<PokemonItemResponse>
)

data class PokemonItemResponse(
    val name: String,
    val url: String
)
