package com.example.daily_cost_accounting.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.daily_cost_accounting.data.Category
import com.example.daily_cost_accounting.viewModel.CategoryViewModel


@Composable
fun AddCategoryScreen( categoryViewModel: CategoryViewModel, navController: NavController) {
    var category by remember {
        mutableStateOf("")
    }
    var categories = categoryViewModel

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 180.dp), horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Добавить рассход",
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp, 220.dp)
        ) {
            TextField(value = category,
                onValueChange = { category = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                placeholder = { Text(text = "Введите название категории") })
            Button(onClick = { categories.addCategory(Category(category))
                             navController.navigate("/add-expenses")},
                enabled = category.isNotEmpty()
            , modifier = Modifier.fillMaxWidth()
                    .padding(top = 20.dp)) {
                Text(text = "Добавить категорию")
            }
        }
    }
}



@Preview
@Composable
fun AddCategoryPreview(){
    val categoryViewModel = viewModel<CategoryViewModel>()
    val navController = rememberNavController()

    AddCategoryScreen(categoryViewModel, navController)
}