package com.geofriend.ecommercesampleapp.common

import com.geofriend.ecommercesampleapp.network.Status

data class CartItem(val id:String, val title: String, val url:String, val price:String, val qty : Int, val maxQty : Int)
data class CartItems(val items:List<CartItem>, val totalAmount : String)
data class CartResponse(val status:Status, val result : CartItems)