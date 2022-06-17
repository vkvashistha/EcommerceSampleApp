package com.geofriend.ecommercesampleapp.checkout.view

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.geofriend.ecommercesampleapp.R
import com.geofriend.ecommercesampleapp.common.CartItem
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewItem
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolder

class CheckoutViewHolder(itemView: View) : MultiViewViewHolder(itemView) {
    override fun onBindVewHolder(position: Int, multiViewItem: MultiViewItem) {
        super.onBindVewHolder(position, multiViewItem)
        val item = multiViewItem.content as CartItem
        Glide.with(itemView.context).load(item.url).into(itemView.findViewById<AppCompatImageView>(R.id.product_image))
        itemView.findViewById<AppCompatTextView>(R.id.product_title).text = item.title
        itemView.findViewById<AppCompatTextView>(R.id.price).text = item.price
        itemView.findViewById<AppCompatTextView>(R.id.qty).text = itemView.resources.getString(R.string.quantity_added,item.qty)
        itemView.findViewById<AppCompatImageView>(R.id.remove_item).setOnClickListener {
            dispatchEvent(1, position, mapOf(Pair("item", multiViewItem)))
        }
        itemView.findViewById<AppCompatImageView>(R.id.product_image).setOnClickListener {
            dispatchEvent(2, position, mapOf(Pair("item", multiViewItem)))
        }
    }

    override fun attachClickEvent(position: Int, multiViewItem: MultiViewItem) {
        // Do nothing
    }
}