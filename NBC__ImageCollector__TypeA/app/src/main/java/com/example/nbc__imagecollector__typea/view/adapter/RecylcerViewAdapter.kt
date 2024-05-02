package com.example.nbc__imagecollector__typea.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.nbc__imagecollector__typea.databinding.ImageItemBinding
import com.example.nbc__imagecollector__typea.model.KakaoDocuments

class RecylcerViewAdapter(private val items: List<KakaoDocuments>) : RecyclerView.Adapter<RecylcerViewAdapter.RecyclerViewHolder>() {

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    class RecyclerViewHolder(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: KakaoDocuments) {
            binding.apply {
//                ivImage.setImageURI(item.thumbnailUrl.toUri())
                tvType.text = item.collection
                tvDate.text = item.datetime
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val binding = ImageItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RecyclerViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(items[position])

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
        }
    }
}