package com.example.nbc__imagecollector__typea.view.adapter

import android.util.Log
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

    private val TAG = "adapter"

    private lateinit var thisBinding: ImageItemBinding
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun onClick(view: View, item: KakaoDocuments)
    }

    inner class RecyclerViewHolder(private val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: KakaoDocuments) {
            thisBinding = binding
            binding.apply {
                val url = fromString(item.thumbnail_url)
                Glide.with(itemView).load(url).into(binding.ivImage)
                tvType.text = item.display_sitename
                tvDate.text = formatDate(item.datetime!!)

                //DB에 있으면 visible해야하고, DB에 없으면 invisible 해야함
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

            Log.d(TAG, "${position}번째 아이템이 눌림")
            itemClick?.onClick(it, items[position])
        }
    }
}