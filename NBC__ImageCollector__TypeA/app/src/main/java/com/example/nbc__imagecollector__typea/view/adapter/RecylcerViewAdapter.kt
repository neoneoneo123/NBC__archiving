package com.example.nbc__imagecollector__typea.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.nbc__imagecollector__typea.databinding.ImageItemBinding
import com.example.nbc__imagecollector__typea.model.KakaoDocuments
import com.example.nbc__imagecollector__typea.view.util.UtilityFormat.formatDate
import com.example.nbc__imagecollector__typea.view.util.UtilityUrlConverter.fromString


class RecylcerViewAdapter(private val items: List<KakaoDocuments>) : RecyclerView.Adapter<RecylcerViewAdapter.RecyclerViewHolder>() {

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onClick(item: KakaoDocuments)
    }

    class RecyclerViewHolder(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: KakaoDocuments) {
            binding.apply {
                val url = fromString(item.thumbnail_url)
                Glide.with(itemView).load(url).into(binding.ivImage)
                tvType.text = item.display_sitename
                tvDate.text = formatDate(item.datetime!!)
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
            itemClick?.onClick(items[position])
        }
    }
}