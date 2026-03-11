package com.example.cl

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cl.data.api.PokeApiService
import com.example.cl.data.repository.PokemonRepositoryImpl
import com.example.cl.databinding.ActivityMainBinding
import com.example.cl.domain.usecase.GetPokemonListUseCase
import com.example.cl.presentation.PokemonAdapter
import com.example.cl.presentation.PokemonViewModel
import com.example.cl.presentation.PokemonViewModelFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PokemonViewModel
    private val pokemonAdapter = PokemonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
        setupViewModel()
        bindUi()

        viewModel.loadPokemons()
    }

    private fun setupRecyclerView() {
        binding.rvPokemonList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = pokemonAdapter
        }
    }

    private fun setupViewModel() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(PokeApiService::class.java)
        val repository = PokemonRepositoryImpl(service)
        val useCase = GetPokemonListUseCase(repository)
        val factory = PokemonViewModelFactory(useCase)

        viewModel = ViewModelProvider(this, factory)[PokemonViewModel::class.java]
    }

    private fun bindUi() {
        viewModel.state.observe(this) { state ->
            binding.progressBar.visibility = if (state.isLoading) android.view.View.VISIBLE else android.view.View.GONE
            binding.tvError.visibility = if (state.errorMessage != null) android.view.View.VISIBLE else android.view.View.GONE
            binding.tvError.text = state.errorMessage
            pokemonAdapter.submitList(state.pokemons)
        }
    }
}
