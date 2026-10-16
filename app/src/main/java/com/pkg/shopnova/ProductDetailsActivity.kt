package com.pkg.shopnova

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.pkg.data.GetSearchProductDetails
import com.pkg.holder.ProductDataHolder

class ProductDetailsActivity: AppCompatActivity() {
   private lateinit var productList: ArrayList<GetSearchProductDetails>
    lateinit var tvProductTitle: TextView
    lateinit var imgThumbnail: ImageView
    lateinit var tvDescription: TextView
    lateinit var tvCategory: TextView
    lateinit var tvPrice: TextView
    lateinit var tvDiscountPercentage: TextView
    lateinit var tvRating: TextView
    lateinit var tvStock: TextView
  //  lateinit var tvTags: TextView
    lateinit var tvBrand: TextView
    lateinit var tvSku: TextView
    lateinit var tvWeight: TextView
  //  lateinit var tvDimensionList: TextView
    lateinit var tvWarrantyInformation: TextView
    lateinit var tvShippingInformation: TextView
    lateinit var tvAvailabilityStatus: TextView
    lateinit var tvReturnPolicy: TextView

    private var quantity = 1

    lateinit var tvQuantity: TextView
    lateinit var btnDecrease: Button
    lateinit var btnIncrease :Button
    lateinit var btnAddToCart:Button
    var position = 0
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.product_details)

        position = intent.getIntExtra("Position", 0)
        Toast.makeText(
            this,
            "Position $position ",
            Toast.LENGTH_SHORT
        ).show()
        tvProductTitle = findViewById<TextView>(R.id.tvProductTitle)
        imgThumbnail =  findViewById<ImageView>(R.id.imgThumbnail)
        tvDescription =  findViewById<TextView>(R.id.tvDescription)
        tvCategory =  findViewById<TextView>(R.id.tvCategory)
        tvPrice =  findViewById<TextView>(R.id.tvPrice)
        tvDiscountPercentage =  findViewById<TextView>(R.id.tvDiscountPercentage)
        tvRating =  findViewById<TextView>(R.id.tvRating)
        tvStock =  findViewById<TextView>(R.id.tvStock)
        tvBrand =  findViewById<TextView>(R.id.tvBrand)
        tvSku =  findViewById<TextView>(R.id.tvSku)
        tvWeight =  findViewById<TextView>(R.id.tvWeight)
        tvWarrantyInformation =  findViewById<TextView>(R.id.tvWarrantyInformation)
        tvShippingInformation =  findViewById<TextView>(R.id.tvShippingInformation)
        tvAvailabilityStatus =  findViewById<TextView>(R.id.tvAvailabilityStatus)
        tvReturnPolicy =  findViewById<TextView>(R.id.tvReturnPolicy)

        tvQuantity = findViewById<TextView>(R.id.tvQuantity)

        btnDecrease = findViewById<Button>(R.id.btnDecrease)
        btnIncrease = findViewById<Button>(R.id.btnIncrease)
        btnAddToCart = findViewById<Button>(R.id.btnAddToCart)

        setupQuantityAndCart()


        productList = ProductDataHolder.ProductDataHolder.productDetailsList as ArrayList<GetSearchProductDetails>
        // Access product details
        if (productList.isNotEmpty()) {
            val product = productList[position]

            tvProductTitle.text = product.title
           //  tvProductPrice.text = product.price

           //Load Product Image
            Glide.with(imgThumbnail.context)
                .load(product.thumbnail)
                .into(imgThumbnail)

            tvDescription.text = product.description
            tvCategory.text = product.category
            Toast.makeText(
                this,
                "price  ${product.price.get(1)} ",
                Toast.LENGTH_SHORT
            ).show()
            tvPrice.text = "${product.price} ₹"
            tvDiscountPercentage.text = "${product.discountPercentage} %"

            tvRating.text = product.rating
            tvStock.text = product.stock
            tvBrand.text = product.brand
            tvSku.text = product.sku
            tvWeight.text = product.weight
            tvWarrantyInformation.text = product.warrantyInformation
            tvShippingInformation.text = product.shippingInformation
            tvAvailabilityStatus.text = product.availabilityStatus
            tvReturnPolicy.text = product.returnPolicy

        }

        }
     fun setupQuantityAndCart() {
        btnDecrease.setOnClickListener {
            if (quantity > 1) {
                quantity--
                tvQuantity.text = quantity.toString()
            }
        }

        btnIncrease.setOnClickListener {
            quantity++
            tvQuantity.text = quantity.toString()
        }

        btnAddToCart.setOnClickListener {
            Toast.makeText(
                this,
                "Added $quantity item(s) to cart",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

}