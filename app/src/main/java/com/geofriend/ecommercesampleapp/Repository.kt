package com.geofriend.ecommercesampleapp

import com.geofriend.ecommercesampleapp.collection.model.CollectionResponse
import com.geofriend.ecommercesampleapp.common.CartItem
import com.geofriend.ecommercesampleapp.common.CartItems
import com.geofriend.ecommercesampleapp.common.CartResponse
import com.geofriend.ecommercesampleapp.common.User
import com.geofriend.ecommercesampleapp.home.model.ProductCategoryResponse
import com.geofriend.ecommercesampleapp.network.Status
import com.geofriend.ecommercesampleapp.product.model.ProductResponse
import com.google.gson.GsonBuilder

class Repository {
    fun getProductCategories() : ProductCategoryResponse {
        return GsonBuilder().create().fromJson(FakeData.productCategories, ProductCategoryResponse::class.java)
    }

    fun getCollections(collectionId:String) : CollectionResponse {
        return GsonBuilder().create().fromJson(FakeData.productCollection, CollectionResponse::class.java)
    }

    fun getProductInfo(productId:String) : ProductResponse {
        return GsonBuilder().create().fromJson(FakeData.productResponse, ProductResponse::class.java)
    }

    fun getUserCart() : CartResponse {
        return CartResponse(Status(0,"success"), CartItems(User.getCartItems(), getTotal(User.getCartItems()).toString()))
    }

    fun removeCartItem(item:CartItem) : CartResponse {
        User.removeCartItem(item)
        return CartResponse(Status(0,"success"), CartItems(User.getCartItems(), "₹ " + getTotal(User.getCartItems()).toString()))
    }

    private fun getTotal(items : List<CartItem>) : Float {
        val total = 0.0f
        for(item in items) {
            total.plus(item.price.replace("₹","").trim().toFloat()*Math.min(item.qty, item.maxQty))
        }
        return total
    }
}