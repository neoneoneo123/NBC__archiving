package com.example.nbc_standard_week7.presentation.mapper

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodItemModel(
    val name: String?,
    val image: String?,
    val nutrient: String?,
    var like: Boolean = false,
) : Parcelable