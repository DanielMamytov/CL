package com.example.cl

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cl.databinding.ActivityMainBinding
import com.example.cl.presentation.PokemonAdapter
import com.example.cl.presentation.PokemonViewModel

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
        val app = application as PokemonApplication
        val factory = app.appContainer.providePokemonViewModelFactory()
        viewModel = ViewModelProvider(this, factory)[PokemonViewModel::class.java]
    }

    private fun bindUi() {
        viewModel.pokemons.observe(this) { pokemonAdapter.submitList(it) }
    }
}
