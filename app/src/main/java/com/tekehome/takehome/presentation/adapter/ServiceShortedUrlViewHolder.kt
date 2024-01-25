package com.tekehome.takehome.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.tekehome.takehome.databinding.ItemShortedUrlBinding
import com.tekehome.takehome.domain.entity.ServiceAliases

class ServiceShortedUrlViewHolder(private val binding: ItemShortedUrlBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: ServiceAliases) {
        setUpView(item)
    }

    private fun setUpView(item: ServiceAliases) {
        with(item) {
            binding.apply {
                aliasServiceTextView.text = alias
                selfServiceTextView.text = self
                shortServiceTextView.text = shortUrl
                shortServiceTextView.isSelected = true
                selfServiceTextView.isSelected = true
            }
        }
    }
}
