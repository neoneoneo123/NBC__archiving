package com.example.nbc_standard_week7.presentation.mapper

import com.example.nbc_standard_week7.data.remote.model.B553748Items
import com.example.nbc_standard_week7.presentation.model.FoodItem

fun List<B553748Items>.asFoodItemModel(): List<FoodItem> {
    return map {
        FoodItem(
            it.item.prdlstNm,
            it.item.imgurl1,
            it.item.nutrient,
            false
        )
    }
}