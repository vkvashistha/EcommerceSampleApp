package com.geofriend.ecommercesampleapp.home.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geofriend.ecommercesampleapp.R
import com.geofriend.ecommercesampleapp.Repository
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewItem
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolder

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()
    private val productCategories = MutableLiveData<List<MultiViewItem>>()
    fun getProductCategories() : LiveData<List<MultiViewItem>> {
        val productCategoryResponse = repository.getProductCategories()
        if(productCategoryResponse.status.code == 0) {
            val items = ArrayList<MultiViewItem>()
            for(item in productCategoryResponse.result) {
                if(item.size == 1) {
                    items.add(MultiViewItem(R.layout.item_product_category, item[0]))
                } else {
                    items.add(MultiViewItem(R.layout.horizontal_list, item))
                }
            }
            productCategories.postValue(items)
        }
        return productCategories
    }
}