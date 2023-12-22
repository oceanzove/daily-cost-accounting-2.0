package com.example.daily_cost_accounting.network

import com.example.daily_cost_accounting.data.Joke
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val BASE_URL = "https://official-joke-api.appspot.com/"

interface JokeApiService {
    @GET("random_joke")
    suspend fun getJoke(): Joke
}

private val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val jokeApiService = retrofit.create(JokeApiService::class.java)