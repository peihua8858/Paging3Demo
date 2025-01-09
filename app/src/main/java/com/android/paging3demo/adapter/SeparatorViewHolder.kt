package com.android.paging3demo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.paging3demo.databinding.SeparatorViewItemBinding

class SeparatorViewHolder(private val binding: SeparatorViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(separatorText: String) {
        binding.separatorDescription.text = separatorText
    }

    companion object {
        fun create(parent: ViewGroup): SeparatorViewHolder {
            return SeparatorViewHolder(SeparatorViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }
    }
}
