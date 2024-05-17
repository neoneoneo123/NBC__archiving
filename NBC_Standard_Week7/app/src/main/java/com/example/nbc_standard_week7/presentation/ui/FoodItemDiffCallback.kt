package com.example.nbc_standard_week7.presentation.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.nbc_standard_week7.presentation.mapper.FoodItemModel

class FoodItemDiffCallback : DiffUtil.ItemCallback<FoodItemModel>() {
    override fun areItemsTheSame(oldItem: FoodItemModel, newItem: FoodItemModel): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: FoodItemModel, newItem: FoodItemModel): Boolean {
        return oldItem == newItem
    }

}