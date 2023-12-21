package com.example.daily_cost_accounting.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daily_cost_accounting.data.Joke
import com.example.daily_cost_accounting.network.JokeApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch


class JokeViewModel : ViewModel() {
    private val _joke = MutableStateFlow<Joke?>(null)
    val joke: StateFlow<Joke?> = _joke.asStateFlow()

    fun loadJoke() {
        viewModelScope.launch {
            try {
                val joke = JokeApiService.create().getRandomJoke()
                _joke.value = joke
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
