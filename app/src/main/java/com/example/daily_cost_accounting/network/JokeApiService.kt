package com.example.daily_cost_accounting.network

import com.example.daily_cost_accounting.data.Joke
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JokeApiService {
    @GET("random_joke")
    suspend fun getRandomJoke(): Joke

    companion object {
        private const val BASE_URL = "https://official-joke-api.appspot.com/random_joke"

        fun create(): JokeApiService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JokeApiService::class.java)
        }
    }
}