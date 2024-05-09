package com.example.nbc_standard_week7.presentation.repository

import com.example.nbc_standard_week7.data.DTO.B553748Items
import com.example.nbc_standard_week7.presentation.model.FoodItemModel

interface FoodItemRepository {
    suspend fun b553748Search(pageNo: Int, numOfRows:Int, prdlstNm: String): List<FoodItemModel>
}

