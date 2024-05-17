package com.example.nbc_standard_week7.presentation.ui

import androidx.recyclerview.widget.DiffUtil
import com.example.nbc_standard_week7.presentation.model.FoodItem

class FoodItemDiffCallback : DiffUtil.ItemCallback<FoodItem>() {
    override fun areItemsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem.image == newItem.image
    }

    override fun areContentsTheSame(oldItem: FoodItem, newItem: FoodItem): Boolean {
        return oldItem == newItem
    }

}