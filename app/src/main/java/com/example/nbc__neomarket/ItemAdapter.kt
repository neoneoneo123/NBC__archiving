package com.example.nbc__neomarket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbc__neomarket.databinding.ItemBinding
import com.example.nbc__neomarket.itemData.Item
import java.text.DecimalFormat


class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ItemViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.ivItem.setImageResource(item.image)
            binding.tvTitle.text = item.title
            binding.tvAdress.text = item.address
            binding.tvPrice.text = DecimalFormat("#,###원").format(item.price)
            binding.tvChat.text = item.chat.toString()
            binding.tvLike.text = item.like.toString()
        }
    }
}