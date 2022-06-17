package com.geofriend.ecommercesampleapp.home.view

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.geofriend.ecommercesampleapp.R
import com.geofriend.ecommercesampleapp.home.model.ProductCategory
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewItem
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolder

class ProductCategoryViewHolder(itemView: View) : MultiViewViewHolder(itemView) {
    override fun onBindVewHolder(position: Int, multiViewItem: MultiViewItem) {
        super.onBindVewHolder(position, multiViewItem)
        Glide.with(itemView.context).load((multiViewItem.content as ProductCategory).url).into(itemView.findViewById(R.id.banner));
        itemView.findViewById<AppCompatImageView>(R.id.banner).setOnClickListener {
            dispatchEvent(0, position, mapOf(Pair("item", multiViewItem)))
        }
    }
}