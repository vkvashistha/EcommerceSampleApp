package com.geofriend.ecommercesampleapp.collection.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geofriend.ecommercesampleapp.R
import com.geofriend.ecommercesampleapp.Repository
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewItem

class CollectionViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()
    fun getCollection(collectionId:String) : LiveData<List<MultiViewItem>> {
        val collectionLiveData = MutableLiveData<List<MultiViewItem>>()
        val result = repository.getCollections(collectionId).result
        val items = ArrayList<MultiViewItem>()
        result?.let {
            for(item in result) {
                items.add(MultiViewItem(R.layout.item_product_catelogue, item))
            }
            collectionLiveData.postValue(items)
        }
        return collectionLiveData
    }
}