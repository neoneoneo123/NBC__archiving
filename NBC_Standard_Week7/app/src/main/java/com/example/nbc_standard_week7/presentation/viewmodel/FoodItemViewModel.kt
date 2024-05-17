package com.example.nbc_standard_week7.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbc_standard_week7.presentation.model.FoodItem
import com.example.nbc_standard_week7.presentation.repository.FoodItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FoodItemViewModel @Inject constructor (
    private val foodItemRepository: FoodItemRepository
) : ViewModel() {

    private val _getFoodItemSearchResult: MutableLiveData<List<FoodItem>> = MutableLiveData()
    val getFoodItemSearchResult: LiveData<List<FoodItem>> get() = _getFoodItemSearchResult

    fun getFoodItemList(pageNo: Int, numOfRows:Int, prdlstNm: String) = viewModelScope.launch {
        val result = withContext(Dispatchers.IO) {
            foodItemRepository.foodSearch(pageNo, numOfRows, prdlstNm)
        }

        withContext(Dispatchers.Main) {
            _getFoodItemSearchResult.value = result
        }
    }

    fun setFoodItemList(newList: List<FoodItem>) {
        _getFoodItemSearchResult.value = newList
    }
}