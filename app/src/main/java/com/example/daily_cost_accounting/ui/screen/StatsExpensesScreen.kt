package com.example.daily_cost_accounting.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.daily_cost_accounting.viewModel.CategoryViewModel
import com.example.daily_cost_accounting.viewModel.ExpensesViewModel


@Composable
fun StatsExpensesScreen(expensesViewModel: ExpensesViewModel, categoryViewModel: CategoryViewModel, navController: NavController){
    val categories = categoryViewModel.categories

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp)
            , horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Статистика"
                , color = Color.White
                , fontSize = 36.sp
            , modifier = Modifier
            )
        }
        Column (
            modifier = Modifier
                .padding(30.dp, 120.dp, 30.dp, 10.dp)
                .fillMaxSize()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(15.dp)
            ){
                items(categories){
                    category ->
                    Card (modifier = Modifier.padding(top = 10.dp)) {
                        Row (modifier = Modifier.fillMaxWidth()) {
                            Text(text = category.name,
                                modifier = Modifier.padding(start = 10.dp))
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = expensesViewModel.expenses.filter {
                                it.category == category.name
                            }.sumOf { it.cost}.toString() + " ₽",
                                modifier = Modifier.padding(end = 40.dp))
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = {
                navController.navigate("/expenses")}
                , modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)) {
                Text(text = "Вернуться к рассходам")
            }
        }
    }
}

@Preview
@Composable
fun StatsExpensesPreview(){
    val expensesViewModel = viewModel<ExpensesViewModel>()
    val categoryViewModel = viewModel<CategoryViewModel>()
    val navController = rememberNavController()

    StatsExpensesScreen(expensesViewModel, categoryViewModel, navController)
}