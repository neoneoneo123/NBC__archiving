package com.example.nbc_standard_week7.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodItem(
    val name: String?,
    val image: String?,
    val nutrient: String?,
    var like: Boolean = false,
) : Parcelable