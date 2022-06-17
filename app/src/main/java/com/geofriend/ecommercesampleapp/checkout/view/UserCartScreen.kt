package com.geofriend.ecommercesampleapp.checkout.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.geofriend.ecommercesampleapp.R
import com.geofriend.ecommercesampleapp.checkout.viewmodel.CheckoutViewModel
import com.geofriend.ecommercesampleapp.common.CartItem
import com.geofriend.ecommercesampleapp.common.CartResponse
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewItem
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewRecyclerViewAdapter
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolder
import com.geofriend.ecommercesampleapp.view.multiviewrecyclerview.MultiViewViewHolderProvider

class UserCartScreen : Fragment() {
    private val checkoutViewModel by lazy { ViewModelProvider(this)[CheckoutViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_cart_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.btn_proceed_checkout)
        button.isEnabled = false
        val viewHolderProivder = MultiViewViewHolderProvider()
        viewHolderProivder.registerViewHolderClass(R.layout.cart_item, CheckoutViewHolder::class.java)
        val cartAdapter = MultiViewRecyclerViewAdapter(viewHolderProivder)
        checkoutViewModel.getCartItems().observe(viewLifecycleOwner) { cartResponse->
            view.findViewById<RecyclerView>(R.id.cart_items).apply {
                adapter = cartAdapter
                layoutManager = LinearLayoutManager(requireContext())
            }
            cartAdapter.submitList(getCartMultiViewItem(cartResponse))
            button.isEnabled = true
            button.setOnClickListener {
                val args = Bundle()
                args.putString("final_amount", cartResponse.result.totalAmount)
                findNavController().navigate(R.id.orderCheckoutScreen)
            }
        }
        cartAdapter.addEventListener(object : MultiViewViewHolder.EventListener {
            override fun onEventOccurred(
                eventType: Int,
                position: Int,
                eventParams: Map<String, Any>
            ) {
                val item = (eventParams["item"] as MultiViewItem).content as CartItem
                if(eventType == 1) {
                    checkoutViewModel.removeCartItem((eventParams["item"] as MultiViewItem).content as CartItem)
                } else if(eventType == 2) {
                    val args = Bundle()
                    args.putString("productId", item.id)
                    findNavController().navigate(R.id.productScreen,args)
                }
            }
        })
    }

    private fun getCartMultiViewItem(cartResponse : CartResponse) : List<MultiViewItem> {
        val items = ArrayList<MultiViewItem>()
        for(item in cartResponse.result.items) {
            items.add(MultiViewItem(R.layout.cart_item, item))
        }
        return items
    }
}