package com.example.nbc__neomarket

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbc__neomarket.databinding.ItemBinding
import com.example.nbc__neomarket.itemData.Item
import java.text.DecimalFormat

class ItemAdapter(private val items: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null

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

        //아이템 선택
        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
            Log.d("여기는 어댑터", "${position}이 눌렸습니다.")
        }
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