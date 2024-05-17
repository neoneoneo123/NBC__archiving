package com.example.nbc_standard_week7.data.repository

import com.example.nbc_standard_week7.network.B553748ApiServiceClient
import com.example.nbc_standard_week7.presentation.mapper.asFoodItemModel
import com.example.nbc_standard_week7.presentation.mapper.FoodItemModel
import com.example.nbc_standard_week7.presentation.repository.FoodItemRepository

class FoodItemRepositoryImpl : FoodItemRepository {
    override suspend fun b553748Search(pageNo: Int, numOfRows: Int, prdlstNm: String): List<FoodItemModel> {
        return B553748ApiServiceClient.b553748NetWork.getB553748(pageNo, numOfRows, prdlstNm).body.items.asFoodItemModel()
    }
}