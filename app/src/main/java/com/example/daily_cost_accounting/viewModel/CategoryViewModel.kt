package com.example.daily_cost_accounting.viewModel

import androidx.lifecycle.ViewModel
import com.example.daily_cost_accounting.data.Category

class CategoryViewModel: ViewModel() {
    private val _categories = mutableListOf(Category("Food"), Category("Auto"))

    val categories: List<Category>
        get() = _categories

    fun addCategory(category: Category){
        _categories.add(category)
    }

    fun removeCategory(category: Category){
        _categories.remove(category)
    }
}