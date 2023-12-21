package com.example.daily_cost_accounting.ui.screen

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.daily_cost_accounting.data.Category
import com.example.daily_cost_accounting.data.Expenses
import com.example.daily_cost_accounting.viewModel.ExpensesViewModel


@SuppressLint("ResourceType")
@Composable
fun ExpensesListScreen(expensesViewModel: ExpensesViewModel, navController: NavController){
    val expenses = expensesViewModel.expenses

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
            , horizontalArrangement = Arrangement.Center
        ) {
            Text("Рассходы "
                ,color = Color.White
                , fontSize = 42.sp
                , fontWeight = FontWeight.Bold
            )
            Icon(Icons.Default.ShoppingCart
                ,contentDescription = null
                , tint = Color.White
                , modifier = Modifier
                    .padding(top = 20.dp)
                    .scale(2f)
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp, 80.dp, 30.dp, 10.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    , contentPadding = PaddingValues(10.dp)
            ){
                items(expenses){ expense ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ){
                        Row {
                            Icon(Icons.Default.KeyboardArrowRight, contentDescription = null)
                            Text(text = expense.cost.toString() + " ₽ ")
                            Spacer(modifier = Modifier.weight(1f))
                            Text(text = "Категория: " + expense.category,
                                modifier = Modifier.padding(end = 10.dp))
                            IconButton(onClick = { expensesViewModel.removeExpenses(expense)
                                navController.navigate("/expenses")}) {
                                Icon(Icons.Default.Delete, contentDescription = null)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { navController.navigate("/add-expenses") },
                modifier = Modifier
                    .fillMaxWidth()) {
                Text(text = "Добавить рассход")
            }
            Button(onClick = { navController.navigate("/stats") },
                modifier = Modifier
                    .fillMaxWidth()) {
                Text(text = "Статистика рассходов")
            }
        }
    }
}

@Preview
@Composable
fun ExpensesListPreview(){
    val expensesViewModel = viewModel<ExpensesViewModel>()
    val navController = rememberNavController()

    ExpensesListScreen(expensesViewModel, navController)
}