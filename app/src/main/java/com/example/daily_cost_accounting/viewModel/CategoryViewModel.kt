package com.example.daily_cost_accounting.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.example.daily_cost_accounting.data.Category

class CategoryViewModel: ViewModel() {
    private val _categories: SnapshotStateList<Category> = mutableStateListOf(
        Category("Питание"),
        Category("Транспорт"),
        Category("Путешествия"),
    )

    val categories: List<Category>
        get() = _categories

    fun addCategory(category: Category){
        _categories.add(category)
    }

    fun removeCategory(category: Category){
        _categories.remove(category)
    }
}