package com.example.nbc_standard_week7.data.repository

import com.example.nbc_standard_week7.data.remote.remote.FoodRemoteDataSource
import com.example.nbc_standard_week7.presentation.mapper.asFoodItemModel
import com.example.nbc_standard_week7.presentation.repository.FoodItemRepository
import javax.inject.Inject

class FoodItemRepositoryImpl @Inject constructor(
    private val foodRemoteDataSource: FoodRemoteDataSource
) : FoodItemRepository {
    override suspend fun foodSearch(pageNo: Int, numOfRows: Int, prdlstNm: String) =
        foodRemoteDataSource.getFoodItems(pageNo, numOfRows, prdlstNm).body.items.asFoodItemModel()
}