package com.example.recipetask

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipetask.Api.RecipeApi
import com.example.recipetask.Api.RecipeApiFilter

import kotlinx.coroutines.launch

enum class RecipeApiStatus { LOADING, ERROR, DONE }
class RecipeListScreenViewModel() : ViewModel() {

    // The external immutable LiveData for the response String

    var filter: String? = null

    private val _status = MutableLiveData<RecipeApiStatus>()

    val status: LiveData<RecipeApiStatus>
        get() = _status


    private val _properties = MutableLiveData<List<RecipeItem>>()
    val properties: LiveData<List<RecipeItem>>
        get() = _properties

    private val _navigateToSelectedProperty = MutableLiveData<RecipeItem>()
    val navigateToSelectedProperty: LiveData<RecipeItem>
        get() = _navigateToSelectedProperty

    init {
        getMarsRealEstateProperties()
    }

    // Handling the API Response
    private fun getMarsRealEstateProperties() {
        viewModelScope.launch {
            _status.value = RecipeApiStatus.LOADING
            try {
                _properties.value = RecipeApi.retrofitService.getProperties()
                _status.value = RecipeApiStatus.DONE
                sort(filter)
            } catch (e: Exception) {
                _status.value = RecipeApiStatus.ERROR
                _properties.value = ArrayList()

            }
        }
    }

    fun displayPropertyDetails(recipeItem: RecipeItem) {
        _navigateToSelectedProperty.value = recipeItem
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    fun sort(filter: String?) {

        when (filter) {

            RecipeApiFilter.SortByCalories.value -> {
                _properties.value = _properties.value?.sortedBy {
                    var num: String = it.calories.split(" ")[0]
                    if (num.isEmpty()) {
                        num = "0"
                    }
                    num.toInt()
                }
            }
            RecipeApiFilter.SortByFats.value -> {
                _properties.value = _properties.value?.sortedBy {
                    var num: String = it.fats.split(" ")[0]
                    if (num.isEmpty()) {
                        num = "0"
                    }
                    num.toInt()
                }
            }
        }

    }

    fun updateFilter(filter: String?) {
        sort(filter)
    }
}
