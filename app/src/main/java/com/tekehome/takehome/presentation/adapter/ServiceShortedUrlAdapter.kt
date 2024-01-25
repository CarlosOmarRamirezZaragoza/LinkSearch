package com.tekehome.takehome.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tekehome.takehome.databinding.ItemShortedUrlBinding
import com.tekehome.takehome.domain.entity.ServiceAliases
import javax.inject.Inject

class ServiceShortedUrlAdapter @Inject constructor() :
    ListAdapter<ServiceAliases, RecyclerView.ViewHolder>(TaskDiffCallBack) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holderShorted = holder as ServiceShortedUrlViewHolder
        holderShorted.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ServiceShortedUrlViewHolder(
            ItemShortedUrlBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }
}

object TaskDiffCallBack : DiffUtil.ItemCallback<ServiceAliases>() {
    override fun areItemsTheSame(
        oldItem: ServiceAliases,
        newItem: ServiceAliases,
    ): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(
        oldItem: ServiceAliases,
        newItem: ServiceAliases,
    ): Boolean {
        return oldItem == newItem
    }
}
