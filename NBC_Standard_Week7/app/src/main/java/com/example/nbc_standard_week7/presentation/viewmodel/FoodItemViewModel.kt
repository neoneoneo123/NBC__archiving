package com.example.nbc_standard_week7.presentation.viewmodel

import com.example.nbc_standard_week7.data.DTO.B553748Items
import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.nbc_standard_week7.data.repository.FoodItemRepositoryImpl
import com.example.nbc_standard_week7.presentation.model.FoodItemModel
import kotlinx.coroutines.launch

class FoodItemViewModel(private val foodItemRepositoryImpl: FoodItemRepositoryImpl) : ViewModel() {
    /**
     * B553748 한국식품안전관리인증원_HACCP 제품이미지 및 포장지표기정보
     */
    private val _getB553748SearchResult: MutableLiveData<List<FoodItemModel>> = MutableLiveData()
    val getB553748SearchResult: LiveData<List<FoodItemModel>> get() = _getB553748SearchResult

    fun getB553748List(pageNo: Int, numOfRows:Int, prdlstNm: String) = viewModelScope.launch {
        _getB553748SearchResult.value = foodItemRepositoryImpl.b553748Search(pageNo, numOfRows, prdlstNm)
        Log.d("test api B553748", getB553748SearchResult.value.toString())
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