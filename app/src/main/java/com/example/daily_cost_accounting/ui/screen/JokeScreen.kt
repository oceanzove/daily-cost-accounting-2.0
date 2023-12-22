package com.example.daily_cost_accounting.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.daily_cost_accounting.data.Joke
import com.example.daily_cost_accounting.network.jokeApiService
import com.example.daily_cost_accounting.viewModel.JokeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Composable
fun JokeScreen( navController: NavController) {
    val joke = remember { mutableStateOf<Joke?>(null) }
    val error = remember { mutableStateOf<String?>(null) }


    LaunchedEffect(Unit) {
        try {
            withContext(Dispatchers.IO) {
                joke.value = jokeApiService.getJoke()
            }
        } catch (e: Exception) {
            error.value = e.message
        }
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp, 30.dp)
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(100.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
                , contentAlignment = Alignment.Center){
                    if (joke.value != null) {
                        Column {
                            Text(joke.value!!.setup)
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(joke.value!!.punchline, fontWeight = FontWeight.Bold)
                        }
                    } else if (error.value != null) {
                        Text(error.value!!)
                        println(error.value!!)
                    } else {
                        CircularProgressIndicator()
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { navController.navigate("/expenses") },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "хахах смешно")
            }

        }
    }
}

@Preview
@Composable
fun JokePreview(){
    val navController = rememberNavController()

    JokeScreen(navController)
}
