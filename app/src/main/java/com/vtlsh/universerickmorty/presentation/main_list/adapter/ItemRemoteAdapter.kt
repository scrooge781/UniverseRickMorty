package com.vtlsh.universerickmorty.presentation.main_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vtlsh.universerickmorty.R
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
                txtMainTitle.text = item.name
                val colorStatus = binding.root.resources.getColor(item.status.color)
                txtMainTitle.setTextColor(colorStatus)
                txtMainTitle.setOnClickListener {
                    clickCallback.invoke(item)
                }
                Glide
                    .with(binding.root)
                    .load(item.image)
                    .centerCrop()
                    .placeholder(R.drawable.ic_default_icon)
                    .into(ivMainIcon)
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