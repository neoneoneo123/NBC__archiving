package com.example.nbc_standard_week7.presentation.mapper

import com.example.nbc_standard_week7.data.DTO.B553748Items
import com.example.nbc_standard_week7.presentation.model.FoodItemModel

fun List<B553748Items>.asFoodItemModel(): List<FoodItemModel> {
    return map {
        FoodItemModel(
            it.item.prdlstNm,
            it.item.imgurl1,
            it.item.nutrient
        )
    }
}