package com.example.nbc_standard_week7.presentation.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbc_standard_week7.databinding.ItemBinding
import com.example.nbc_standard_week7.presentation.model.FoodItemModel
import com.example.nbc_standard_week7.presentation.util.UtilityUrlConverter.fromString

class FoodItemAdapter() : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    private var items: MutableList<FoodItemModel> = mutableListOf()
    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onClick(view: View, item: FoodItemModel)
    }

    fun setViewItems(items: List<FoodItemModel>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FoodItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FoodItemViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, items[position])
        }
    }

    class FoodItemViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FoodItemModel) {
            binding.apply {
                tvName.text = item.name

                val url = fromString(item.image)
                Glide.with(itemView).load(url).into(binding.ivItem)
            }
        }
    }
}