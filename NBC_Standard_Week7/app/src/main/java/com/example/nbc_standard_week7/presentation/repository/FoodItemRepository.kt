package com.example.nbc_standard_week7.presentation.repository

import com.example.nbc_standard_week7.presentation.model.FoodItem

interface FoodItemRepository {
    suspend fun foodSearch(pageNo: Int, numOfRows:Int, prdlstNm: String): List<FoodItem>
}

