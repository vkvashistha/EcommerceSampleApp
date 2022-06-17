package com.geofriend.ecommercesampleapp.collection.view

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.geofriend.ecommercesampleapp.R
import com.geofriend.ecommercesampleapp.collection.model.Collection
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewItem
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolder

class CollectionViewHolder(itemView: View) : MultiViewViewHolder(itemView) {
    override fun onBindVewHolder(position: Int, multiViewItem: MultiViewItem) {
        super.onBindVewHolder(position, multiViewItem)
        val collection = (multiViewItem.content as Collection)
        Glide.with(itemView.context).load(collection.url).into(itemView.findViewById<AppCompatImageView>(R.id.product_image))
        itemView.findViewById<AppCompatTextView>(R.id.title).text = collection.title
        itemView.findViewById<AppCompatTextView>(R.id.price).text = collection.price
    }
}