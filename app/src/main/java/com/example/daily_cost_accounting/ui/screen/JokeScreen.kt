package com.example.daily_cost_accounting.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.daily_cost_accounting.viewModel.JokeViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun JokeScreen(jokeViewModel: JokeViewModel, navController: NavController) {
    val joke = jokeViewModel.joke.value

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
                Box(modifier = Modifier.fillMaxSize().padding(10.dp)){
                    if (joke != null) {
                        Text(text = joke.setup)
                        Text(text = joke.punchline)
                    } else {
                        Text(text = "Failed to load joke. Check your internet connection and try again.")
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { navController.navigate("/expenses") },
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "хахах смешно")
            }

        }
        LaunchedEffect(jokeViewModel) {
            jokeViewModel.loadJoke()
        }
    }
}

@Preview
@Composable
fun JokePreview(){
    val jokeViewModel = viewModel<JokeViewModel>()
    val navController = rememberNavController()

    JokeScreen(jokeViewModel, navController)
}
