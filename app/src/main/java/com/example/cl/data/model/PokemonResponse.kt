package com.example.cl.data.model

import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    val name: String,
    val sprites: SpritesResponse
)

data class SpritesResponse(
    @SerializedName("front_default")
    val frontDefault: String?
)
