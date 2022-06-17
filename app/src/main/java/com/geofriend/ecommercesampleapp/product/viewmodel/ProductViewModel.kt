package com.geofriend.ecommercesampleapp.product.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geofriend.ecommercesampleapp.Repository
import com.geofriend.ecommercesampleapp.common.CartItem
import com.geofriend.ecommercesampleapp.common.User
import com.geofriend.ecommercesampleapp.product.model.ProductInfo

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()

    fun getProductInfo(productId:String) : LiveData<ProductInfo> {
        val response = repository.getProductInfo(productId)
        val productInfoLiveData = MutableLiveData<ProductInfo>()
        productInfoLiveData.postValue(response.result)
        return productInfoLiveData
    }

    fun addToCart(productInfo: ProductInfo, qty : Int) {
        User.addCartItem(CartItem(productInfo.id, productInfo.title, productInfo.imageUrls[0], productInfo.price, qty, productInfo.maxQty))
    }
}