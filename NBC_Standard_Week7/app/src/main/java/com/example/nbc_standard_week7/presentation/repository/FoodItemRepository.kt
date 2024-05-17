package com.example.nbc_standard_week7.presentation.repository

import com.example.nbc_standard_week7.presentation.mapper.FoodItemModel

interface FoodItemRepository {
    suspend fun b553748Search(pageNo: Int, numOfRows:Int, prdlstNm: String): List<FoodItemModel>
}

