package com.example.nbc__standardtaskweek2_optional

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nbc__standardtaskweek2_optional.databinding.ItemInfoBinding

class InfoAdapter(private val infos: List<Info>) :
    RecyclerView.Adapter<InfoAdapter.InfoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoViewHolder {
        val binding = ItemInfoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return InfoViewHolder(binding)
    }

    override fun getItemCount(): Int = infos.size

    override fun onBindViewHolder(holder: InfoViewHolder, position: Int) {
        holder.bind(infos[position])
    }

    class InfoViewHolder(private val binding: ItemInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(info: Info) {
            binding.tvNumber.text = info.number
            binding.tvDescription.text = info.description
        }
    }
}