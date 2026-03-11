package com.example.cl.di

import com.example.cl.data.api.PokeApiService
import com.example.cl.data.repository.PokemonRepositoryImpl
import com.example.cl.domain.repository.PokemonRepository
import com.example.cl.domain.usecase.GetPokemonListUseCase
import com.example.cl.presentation.PokemonViewModelFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val pokeApiService: PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }

    private val pokemonRepository: PokemonRepository by lazy {
        PokemonRepositoryImpl(pokeApiService)
    }

    private val getPokemonListUseCase: GetPokemonListUseCase by lazy {
        GetPokemonListUseCase(pokemonRepository)
    }

    fun providePokemonViewModelFactory(): PokemonViewModelFactory {
        return PokemonViewModelFactory(getPokemonListUseCase)
    }
}
