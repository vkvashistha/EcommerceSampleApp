package com.geofriend.ecommercesampleapp.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object User {
    private val cartItems = ArrayList<CartItem>()
    private val cartItemsLiveData = MutableLiveData<ArrayList<CartItem>>()
    fun addCartItem(item : CartItem) {
        cartItems.add(item)
        cartItemsLiveData.postValue(cartItems)
    }

    fun removeCartItem(item : CartItem) {
        cartItems.remove(item)
        cartItemsLiveData.postValue(cartItems)
    }

    fun getCartItemsObserver() : LiveData<ArrayList<CartItem>> {
        return cartItemsLiveData
    }

    fun getCartItems() : List<CartItem> {
        return cartItems
    }

    fun clearCart() {
        cartItems.clear()
        cartItemsLiveData.postValue(cartItems)
    }
}