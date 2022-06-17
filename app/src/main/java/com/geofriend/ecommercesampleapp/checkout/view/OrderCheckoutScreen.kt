package com.geofriend.ecommercesampleapp.checkout.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.navigation.fragment.findNavController
import com.geofriend.ecommercesampleapp.R
import com.geofriend.ecommercesampleapp.common.User
import com.geofriend.ecommercesampleapp.view.BaseFragment

class OrderCheckoutScreen : BaseFragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_checkout_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<AppCompatTextView>(R.id.final_amount).text = arguments?.getString("final_amount", "Some error occurred, please reload this screen")
        view.findViewById<Button>(R.id.btn_confirm_and_proceed).setOnClickListener {
            Toast.makeText(requireContext(), "Successfully Placed Your Order", Toast.LENGTH_SHORT).show()
            User.clearCart()
            findNavController().navigate(R.id.homeScreen)
        }
    }
}