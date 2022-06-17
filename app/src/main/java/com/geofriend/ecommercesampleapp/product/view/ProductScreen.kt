package com.geofriend.ecommercesampleapp.product.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.geofriend.ecommercesampleapp.R
import com.geofriend.ecommercesampleapp.product.viewmodel.ProductViewModel

class ProductScreen : Fragment() {

    private val productViewModel by lazy { ViewModelProvider(this)[ProductViewModel::class.java] }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<View>(R.id.product_layout).visibility = View.GONE
        view.findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
        productViewModel.getProductInfo("").observe(viewLifecycleOwner) { productInfo ->
            view.findViewById<View>(R.id.product_layout).visibility = View.VISIBLE
            view.findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
            Glide.with(requireContext()).load(productInfo.imageUrls[0]).into(view.findViewById<ImageView>(R.id.main_image))
            Glide.with(requireContext()).load(productInfo.imageUrls[0]).into(view.findViewById<ImageView>(R.id.image_thumb1))
            if(productInfo.imageUrls.size > 1) {
                Glide.with(requireContext()).load(productInfo.imageUrls[1])
                    .into(view.findViewById<ImageView>(R.id.image_thumb2))
            }

            if(productInfo.imageUrls.size > 2) {
                Glide.with(requireContext()).load(productInfo.imageUrls[2])
                    .into(view.findViewById<ImageView>(R.id.image_thumb3))
            }

            view.findViewById<AppCompatTextView>(R.id.tv_title).text = productInfo.title
            view.findViewById<AppCompatTextView>(R.id.tv_price).text = productInfo.price
            view.findViewById<RatingBar>(R.id.product_rating).rating = productInfo.rating.toFloat()
            val qtys = getIntArray(1,productInfo.maxQty, 1).toList()
            view.findViewById<Spinner>(R.id.qty).adapter = ArrayAdapter<Int>(requireContext(), R.layout.item_qty, qtys)
            view.findViewById<AppCompatTextView>(R.id.tv_product_description).text = productInfo.description
            view.findViewById<Button>(R.id.btn_add_to_cart).setOnClickListener {
                productViewModel.addToCart(productInfo, view.findViewById<Spinner>(R.id.qty).selectedItem as Int)
                Toast.makeText(requireContext(), "Successfully Added", Toast.LENGTH_SHORT).show()
            }

        }

    }
    private fun getIntArray(start:Int, end:Int, step:Int) : IntArray{
        var i = start
        var size = (end - start + 1)/step
        var array = IntArray(size) { (it + start)
        }
        return array
    }
}