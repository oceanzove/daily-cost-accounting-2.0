package com.example.daily_cost_accounting.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.daily_cost_accounting.data.Expenses
import com.example.daily_cost_accounting.viewModel.CategoryViewModel
import com.example.daily_cost_accounting.viewModel.ExpensesViewModel

@Composable
fun AddExpensesScreen(expensesViewModel: ExpensesViewModel, categoryViewModel: CategoryViewModel, navController: NavController){
    var cost by remember {
        mutableDoubleStateOf(0.0)
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    var seletedCategory by remember {
        mutableStateOf("")
    }

    val categories = categoryViewModel.categories

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 180.dp)
                , horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Добавить рассход"
                ,color = Color.White
                , fontSize = 30.sp
                , fontWeight = FontWeight.Bold)
        }
        Column (modifier = Modifier
            .fillMaxSize()
            .padding(40.dp, 220.dp)) {
            TextField(value = cost.toString()
                ,onValueChange = { cost = it.toDouble()}
                , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
                , modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            )
            Box (modifier = Modifier.padding(top = 5.dp)) {
                TextButton(onClick = { expanded = true}
                    , modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                ) {
                    Text(seletedCategory.ifEmpty { "Выберите категорию" },
                        textAlign = TextAlign.Start
                        , color = Color.Black
                    )
                }
                DropdownMenu(expanded = expanded
                    , onDismissRequest = { expanded = false }
                    , modifier = Modifier.fillMaxWidth(0.8f)) {
                    categories.forEach{
                            category ->
                        DropdownMenuItem(text = {
                            Row {
                                Text(text = category.name )
                                Spacer(modifier = Modifier.weight(1f))
                                IconButton(onClick = { categoryViewModel.removeCategory(category)
                                 navController.navigate("/expenses")
                                }) {
                                    Icon(Icons.Rounded.Delete, contentDescription = null)
                                }
                            }
                        }
                            , onClick = {
                                seletedCategory = category.name
                                expanded = false
                            })
                    }
                    TextButton(onClick = {
                        expanded = false
                        navController.navigate("/add-category")
                    }) {
                        Text(text = "Добавить категорию", color = Color.Black)
                    }
                }
            }
            Button(onClick = {expensesViewModel.addExpenses(Expenses(cost, seletedCategory))
            navController.navigate("/expenses")}
                , modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)) {
                Text(text = "Добавить рассход")
            }
        }
    }
}

@Preview
@Composable
fun AddExpensesPreview(){
    val expensesViewModel = viewModel<ExpensesViewModel>()
    val categoryViewModel = viewModel<CategoryViewModel>()
    val navController = rememberNavController()

    AddExpensesScreen(expensesViewModel, categoryViewModel, navController)
}