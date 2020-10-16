package com.example.recipetask

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class DetailViewModelFactory(
    private val recipeItem: RecipeItem,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(recipeItem, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
