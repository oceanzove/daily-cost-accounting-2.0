package com.example.daily_cost_accounting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.daily_cost_accounting.ui.screen.AddCategoryScreen
import com.example.daily_cost_accounting.ui.screen.AddExpensesScreen
import com.example.daily_cost_accounting.ui.screen.ExpensesListScreen
import com.example.daily_cost_accounting.ui.screen.JokeScreen
import com.example.daily_cost_accounting.ui.screen.StatsExpensesScreen
import com.example.daily_cost_accounting.viewModel.CategoryViewModel
import com.example.daily_cost_accounting.viewModel.ExpensesViewModel
import com.example.daily_cost_accounting.viewModel.JokeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val expensesViewModel = viewModel<ExpensesViewModel>()
            val categoryViewModel = viewModel<CategoryViewModel>()
            val navController = rememberNavController()
            
            NavHost(navController = navController, startDestination = "/joke"){
                composable("/joke"){
                    JokeScreen(navController)
                }
                composable("/expenses"){
                    ExpensesListScreen(expensesViewModel, navController)
                }
                composable("/add-expenses"){
                    AddExpensesScreen(expensesViewModel, categoryViewModel, navController)
                }
                composable("/add-category"){
                    AddCategoryScreen(categoryViewModel, navController)
                }
                composable("/stats"){
                    StatsExpensesScreen(expensesViewModel, categoryViewModel, navController)
                }
            }
        }
    }
}

