package com.example.recipetask

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DetailViewModel(recipeItem:  RecipeItem, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<RecipeItem>()

    // The external LiveData for the SelectedProperty
    val selectedProperty: LiveData<RecipeItem>
        get() = _selectedProperty

    init {
        _selectedProperty.value = recipeItem
    }


}