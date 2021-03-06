package com.geofriend.ecommercesampleapp.view.multiviewrecyclerview

import androidx.recyclerview.widget.DiffUtil

class ItemDiffCallback : DiffUtil.ItemCallback<MultiViewItem>() {
    override fun areItemsTheSame(oldItem: MultiViewItem, newItem: MultiViewItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: MultiViewItem, newItem: MultiViewItem): Boolean {
        return oldItem.content == newItem.content
    }
}