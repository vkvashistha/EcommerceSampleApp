package com.geofriend.ecommercesampleapp.checkout.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.geofriend.ecommercesampleapp.Repository
import com.geofriend.ecommercesampleapp.common.CartItem
import com.geofriend.ecommercesampleapp.common.CartResponse

class CheckoutViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository()
    private val cartResponseLiveData = MutableLiveData<CartResponse>()
    fun getCartItems() : LiveData<CartResponse> {
        cartResponseLiveData.postValue(repository.getUserCart())
        return cartResponseLiveData
    }

    fun removeCartItem(item : CartItem) {
        cartResponseLiveData.postValue(repository.removeCartItem(item))
    }
}