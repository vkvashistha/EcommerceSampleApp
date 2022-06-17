package com.geofriend.ecommercesampleapp.home.view

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geofriend.ecommercesampleapp.R
import com.geofriend.ecommercesampleapp.home.model.ProductCategory
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewItem
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewRecyclerViewAdapter
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolder
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolderProvider

class ProductCategoryHorizontalListViewHolder(itemView: View) : MultiViewViewHolder(itemView) {
    override fun onBindVewHolder(position: Int, multiViewItem: MultiViewItem) {
        super.onBindVewHolder(position, multiViewItem)
        setAdapterIfRequired()
        val adapter = itemView.findViewById<RecyclerView>(R.id.horizontalList).adapter as MultiViewRecyclerViewAdapter
        if(multiViewItem.content is MutableList<*>) {
            val list = multiViewItem.content as List<ProductCategory>
            val items = ArrayList<MultiViewItem>()
            for(item in list) {
                items.add(MultiViewItem(R.layout.item_product_subcategory, item))
            }
            adapter.submitList(items)
        }
    }

    private fun setAdapterIfRequired() {
        if(itemView.findViewById<RecyclerView>(R.id.horizontalList).adapter == null) {
            val baseViewHolderProvider =
                MultiViewViewHolderProvider()
            baseViewHolderProvider.registerViewHolderClass(
                R.layout.item_product_subcategory,
                ProductCategoryViewHolder::class.java
            )
            val productCategoryAdapter =
                MultiViewRecyclerViewAdapter(
                    baseViewHolderProvider
                )
            itemView.findViewById<RecyclerView>(R.id.horizontalList).apply {
                adapter = productCategoryAdapter
                layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            }
        }
    }
}