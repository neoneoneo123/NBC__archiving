package com.example.nbc_standard_week7.presentation.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Switch
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbc_standard_week7.R
import com.example.nbc_standard_week7.databinding.ItemBinding
import com.example.nbc_standard_week7.presentation.model.FoodItem
import com.example.nbc_standard_week7.presentation.ui.FoodItemDiffCallback
import com.example.nbc_standard_week7.presentation.util.UtilityUrlConverter.fromString

class FoodItemAdapter() : RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder>() {

    private var items: MutableList<FoodItem> = mutableListOf()
    private var switchChangeListener: OnSwitchChangeListener? = null
    private val diffCallback = FoodItemDiffCallback()

    fun setViewItems(newItems: List<FoodItem>) {
        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int = items.size
            override fun getNewListSize(): Int = newItems.size
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                diffCallback.areItemsTheSame(items[oldItemPosition], newItems[newItemPosition])
            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                diffCallback.areContentsTheSame(items[oldItemPosition], newItems[newItemPosition])
        })

        this.items.clear()
        this.items.addAll(newItems)
        diffResult.dispatchUpdatesTo(this)
    }

    fun setOnSwitchChangeListener(listener: OnSwitchChangeListener) {
        this.switchChangeListener = listener
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
    }

    inner class FoodItemViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseSwitchCompatOrMaterialCode")
        val switch: Switch = itemView.findViewById(R.id.s_like)

        init {
            switch.setOnCheckedChangeListener { _, isChecked ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    switchChangeListener?.onSwitchChanged(items[position], isChecked)
                }
            }
        }

        fun bind(item: FoodItem) {
            binding.apply {
                tvName.text = item.name

                if (item.like) {
                    sLike.isChecked = true
                } else {
                    sLike.isChecked = false
                }

                val url = fromString(item.image)
                Glide.with(itemView).load(url).into(binding.ivItem)
            }
        }
    }

    interface OnSwitchChangeListener {
        fun onSwitchChanged(item: FoodItem, isChecked: Boolean)
    }
}