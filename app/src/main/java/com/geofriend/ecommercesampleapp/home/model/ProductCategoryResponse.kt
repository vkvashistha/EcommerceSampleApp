package com.geofriend.ecommercesampleapp.home.model

import com.geofriend.ecommercesampleapp.network.Status

data class ProductCategoryResponse(val status: Status, val result : List<List<ProductCategory>>)