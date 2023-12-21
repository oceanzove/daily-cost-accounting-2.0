package com.example.daily_cost_accounting.viewModel

import androidx.lifecycle.ViewModel
import com.example.daily_cost_accounting.data.Expenses

class ExpensesViewModel: ViewModel() {
    private val _expenses = mutableListOf<Expenses>()

    val expenses: List<Expenses>
        get() = _expenses

    fun addExpenses(expenses: Expenses){
        _expenses.add(expenses)
    }

    fun removeExpenses(expenses: Expenses){
        _expenses.remove(expenses)
    }
}