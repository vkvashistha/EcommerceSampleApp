package com.geofriend.ecommercesampleapp.collection.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geofriend.ecommercesampleapp.R
import com.geofriend.ecommercesampleapp.collection.model.Collection
import com.geofriend.ecommercesampleapp.collection.viewmodel.CollectionViewModel
import com.geofriend.ecommercesampleapp.view.BaseFragment
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewItem
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewRecyclerViewAdapter
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolder
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolderProvider

class CollectionScreen : BaseFragment() {
    private val collectionViewModel by lazy { ViewModelProvider(this)[CollectionViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_collection_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val catalogueList = view.findViewById<RecyclerView>(R.id.catelogue)
        val baseViewHolderProvider = MultiViewViewHolderProvider()
        baseViewHolderProvider.registerViewHolderClass(R.layout.item_product_catelogue, CollectionViewHolder::class.java)
        val collectionAdapter = MultiViewRecyclerViewAdapter(baseViewHolderProvider)
        catalogueList.apply {
            adapter = collectionAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        collectionAdapter.addEventListener(object : MultiViewViewHolder.EventListener {
            override fun onEventOccurred(
                eventType: Int,
                position: Int,
                eventParams: Map<String, Any>
            ) {
                if(eventType == 0) {
                    val productId = ((eventParams["item"] as MultiViewItem).content as Collection).id
                    val args= Bundle()
                    args.putString("productId", productId)
                    findNavController().navigate(R.id.productScreen, args)
                }
            }
        })
        collectionViewModel.getCollection("").observe(viewLifecycleOwner) { collection ->
            collectionAdapter.submitList(collection)
        }
    }
}