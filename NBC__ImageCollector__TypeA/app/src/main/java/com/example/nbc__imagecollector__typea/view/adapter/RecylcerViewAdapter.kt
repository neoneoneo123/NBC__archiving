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

class RecylcerViewAdapter(
    fragments: String,
) : RecyclerView.Adapter<RecylcerViewAdapter.RecyclerViewHolder>() {

    private val IMAGE_SEARCH_FRAGMENT = "ImageSearchFragment"
    private val fragment = fragments

    private var roomItems: MutableList<KakaoDocuments> = mutableListOf()
    private var items: MutableList<KakaoDocuments> = mutableListOf()

    var itemClick: ItemClick? = null

    fun setRecyclerViewItems(items: List<KakaoDocuments>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun getRoomItems(roomItems: List<KakaoDocuments>) {
        this.roomItems.clear()
        this.roomItems.addAll(roomItems)
    }

    interface ItemClick {
        fun onClick(view: View, item: KakaoDocuments)
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
            itemClick?.onClick(it, items[position])
            notifyItemChanged(position)
        }
    }

    inner class RecyclerViewHolder(private val binding: ImageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: KakaoDocuments) {
            binding.apply {
                val url = fromString(item.thumbnail_url)
                Glide.with(itemView).load(url).into(binding.ivImage)
                tvType.text = item.display_sitename
                tvDate.text = formatDate(item.datetime!!)

                if (fragment == IMAGE_SEARCH_FRAGMENT) {
                    roomItems.find { it.thumbnail_url == item.thumbnail_url }?.let {
                        ivMark.visibility = View.VISIBLE
                    } ?: run {
                        ivMark.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }
}