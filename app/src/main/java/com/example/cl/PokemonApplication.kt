package com.example.cl

import android.app.Application
import com.example.cl.di.AppContainer

class PokemonApplication : Application() {
    val appContainer: AppContainer by lazy { AppContainer() }
}
