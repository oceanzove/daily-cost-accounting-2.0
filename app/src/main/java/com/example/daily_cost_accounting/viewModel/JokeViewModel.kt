package com.example.daily_cost_accounting.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.daily_cost_accounting.data.Joke
import com.example.daily_cost_accounting.network.JokeApiService
import com.example.daily_cost_accounting.network.jokeApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

import kotlinx.coroutines.launch


class JokeViewModel : ViewModel() {

    init {
        getJoke()
    }

    private fun getJoke() {
        viewModelScope.launch {
            val listResult = jokeApiService.getJoke()
        }
    }
}
