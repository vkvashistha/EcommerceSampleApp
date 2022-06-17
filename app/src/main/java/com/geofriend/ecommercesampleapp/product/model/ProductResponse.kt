package com.geofriend.ecommercesampleapp.product.model

import com.geofriend.ecommercesampleapp.network.Status

data class Sku(val id:String, val name:String, val isAvailable:Boolean)
data class ProductInfo(val id:String, val imageUrls:List<String>, val title : String,  val description: String, val price : String, val variants:List<Sku>, val maxQty : Int, val rating:Float)
data class ProductResponse(val status: Status, val result : ProductInfo?)