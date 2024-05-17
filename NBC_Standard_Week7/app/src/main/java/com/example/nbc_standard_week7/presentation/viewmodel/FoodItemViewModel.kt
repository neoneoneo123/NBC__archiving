package com.example.nbc_standard_week7.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nbc_standard_week7.data.repository.FoodItemRepositoryImpl
import com.example.nbc_standard_week7.presentation.mapper.FoodItemModel
import com.example.nbc_standard_week7.presentation.repository.FoodItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodItemViewModel(private val foodItemRepository: FoodItemRepository) : ViewModel() {

    constructor() : this(FoodItemRepositoryImpl())

    private val _getB553748SearchResult: MutableLiveData<List<FoodItemModel>> = MutableLiveData()
    val getB553748SearchResult: LiveData<List<FoodItemModel>> get() = _getB553748SearchResult

    fun getB553748List(pageNo: Int, numOfRows:Int, prdlstNm: String) = viewModelScope.launch {
        val result = withContext(Dispatchers.IO) {
            foodItemRepository.b553748Search(pageNo, numOfRows, prdlstNm)
        }

        withContext(Dispatchers.Main) {
            _getB553748SearchResult.value = result
        }
    }

    fun setB553748List(newList: List<FoodItemModel>) {
        _getB553748SearchResult.value = newList
    }
}

class FoodItemViewModelFactory : ViewModelProvider.Factory {
    companion object {
        private val repository = FoodItemRepositoryImpl()

        @Suppress("UNCHECKED_CAST")
        fun newInstance(): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(FoodItemViewModel::class.java)) {
                        return FoodItemViewModel(repository) as T
                    }
                    throw IllegalArgumentException("Unknown viewmodel class")
                }
            }
        }
    }
}