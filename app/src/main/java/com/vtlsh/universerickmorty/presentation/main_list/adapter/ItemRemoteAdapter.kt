package com.vtlsh.universerickmorty.presentation.main_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vtlsh.universerickmorty.data.model.Result
import com.vtlsh.universerickmorty.databinding.ItemRemoteBinding
import com.vtlsh.universerickmorty.util.load

class ItemRemoteAdapter(var clickCallback: ((Result, FragmentNavigator.Extras) -> Unit)) :
    ListAdapter<Result, ItemRemoteAdapter.ItemRemoteViewHolder>(ItemRemoteDiffCallback()) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemRemoteAdapter.ItemRemoteViewHolder {
        val binding = ItemRemoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemRemoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemRemoteAdapter.ItemRemoteViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class ItemRemoteViewHolder(private val binding: ItemRemoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Result) {
            binding.apply {
                txtMainTitle.text = item.name
                val colorStatus = binding.root.resources.getColor(item.status.color)
                txtMainTitle.setTextColor(colorStatus)

                ivMainIcon.apply {
                    load(item.image)
                    transitionName = item.image
                }
                val extras = FragmentNavigatorExtras(ivMainIcon to item.image)
                mcMainCard.setOnClickListener { clickCallback.invoke(item, extras) }
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