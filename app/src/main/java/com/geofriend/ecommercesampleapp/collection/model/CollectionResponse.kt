package com.geofriend.ecommercesampleapp.collection.model

import com.geofriend.ecommercesampleapp.network.Status

data class Collection(val id:String, val url:String, val title:String, val price:String)
data class CollectionResponse (val status: Status, val result : List<Collection>?)