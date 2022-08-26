package com.vtlsh.universerickmorty.presentation.main_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vtlsh.universerickmorty.data.model.Result
import com.vtlsh.universerickmorty.databinding.ItemRemoteBinding

class ItemRemoteAdapter(var clickCallback: ((Result) -> Unit)) :
    ListAdapter<Result, ItemRemoteAdapter.ItemRemoteViewHolder>(ItemRemoteDiffCallback()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemRemoteAdapter.ItemRemoteViewHolder {
        var binding = ItemRemoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemRemoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemRemoteAdapter.ItemRemoteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ItemRemoteViewHolder(private val binding: ItemRemoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Result) {
            binding.apply {
                name.text = item.name
                status.text = item.status.value
                status.setOnClickListener {
                    clickCallback.invoke(item)
                }
            }
        }
    }

    class ItemRemoteDiffCallback : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }
}