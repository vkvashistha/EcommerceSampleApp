package com.geofriend.ecommercesampleapp.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geofriend.ecommercesampleapp.R
import com.geofriend.ecommercesampleapp.home.viewmodel.HomeScreenViewModel
import com.geofriend.ecommercesampleapp.view.BaseFragment
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewRecyclerViewAdapter
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolder
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolderProvider

class HomeScreen : BaseFragment() {

    private val homeScreenViewModel : HomeScreenViewModel by lazy { ViewModelProvider(this)[HomeScreenViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val baseViewHolderProvider =
            MultiViewViewHolderProvider()
        baseViewHolderProvider.registerViewHolderClass(R.layout.item_product_category, ProductCategoryViewHolder::class.java)
        baseViewHolderProvider.registerViewHolderClass(R.layout.horizontal_list, ProductCategoryHorizontalListViewHolder::class.java)
        val productCategoryAdapter =
            MultiViewRecyclerViewAdapter(
                baseViewHolderProvider
            )
        productCategoryAdapter.addEventListener(object : MultiViewViewHolder.EventListener {
            override fun onEventOccurred(
                eventType: Int,
                position: Int,
                eventParams: Map<String, Any>
            ) {
                findNavController().navigate(R.id.collectionScreen)
            }

        })
        view.findViewById<RecyclerView>(R.id.homeScreenItems).apply {
            adapter = productCategoryAdapter
            layoutManager = LinearLayoutManager(context)
        }

        homeScreenViewModel.getProductCategories().observe(viewLifecycleOwner) {
            productCategoryAdapter.submitList(it)
        }
    }
}