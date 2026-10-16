package com.pkg.shopnova

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pkg.Adapter.ProductImageAdapter
import com.pkg.data.Dimensions
import com.pkg.data.GetSearchProductDetails
import com.pkg.data.Review
import com.pkg.holder.ProductDataHolder

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var productList: ArrayList<GetSearchProductDetails>
    lateinit var tvProductTitle: TextView
    lateinit var imgThumbnail: ImageView
    lateinit var tvDescription: TextView
    lateinit var tvCategory: TextView
    lateinit var tvPrice: TextView
    lateinit var tvDiscountPercentage: TextView
    lateinit var tvRating: TextView
    lateinit var tvStock: TextView
    lateinit var tvTags: TextView
    lateinit var tvBrand: TextView
    lateinit var tvSku: TextView
    lateinit var tvWeight: TextView
    lateinit var tvDimensionList: TextView
    lateinit var tvWarrantyInformation: TextView
    lateinit var tvShippingInformation: TextView
    lateinit var tvAvailabilityStatus: TextView
    lateinit var tvReviewsList: TextView


    lateinit var tvReturnPolicy: TextView

    lateinit var tvMinimumOrderQuantity: TextView
    lateinit var tvMetaList: TextView
    lateinit var tvImages: ImageView
    lateinit var rvProductImages: RecyclerView



    private var quantity = 1

    lateinit var tvQuantity: TextView
    lateinit var btnDecrease: Button
    lateinit var btnIncrease: Button
    lateinit var btnAddToCart: Button
    var position = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.product_details)

        position = intent.getIntExtra("Position", 0)
        /*Toast.makeText(
            this,
            "Position $position ",
            Toast.LENGTH_SHORT
        ).show()*/
        tvProductTitle = findViewById<TextView>(R.id.tvProductTitle)
        imgThumbnail = findViewById<ImageView>(R.id.imgThumbnail)
        tvDescription = findViewById<TextView>(R.id.tvDescription)
        tvCategory = findViewById<TextView>(R.id.tvCategory)
        tvPrice = findViewById<TextView>(R.id.tvPrice)
        tvDiscountPercentage = findViewById<TextView>(R.id.tvDiscountPercentage)
        tvRating = findViewById<TextView>(R.id.tvRating)
        tvStock = findViewById<TextView>(R.id.tvStock)
        tvTags = findViewById<TextView>(R.id.tvTags)


        tvBrand = findViewById<TextView>(R.id.tvBrand)
        tvSku = findViewById<TextView>(R.id.tvSku)
        tvWeight = findViewById<TextView>(R.id.tvWeight)
        tvDimensionList = findViewById<TextView>(R.id.tvDimensionList)
        tvWarrantyInformation = findViewById<TextView>(R.id.tvWarrantyInformation)
        tvShippingInformation = findViewById<TextView>(R.id.tvShippingInformation)
        tvAvailabilityStatus = findViewById<TextView>(R.id.tvAvailabilityStatus)

        tvReviewsList = findViewById<TextView>(R.id.tvReviewsList)
        tvReturnPolicy = findViewById<TextView>(R.id.tvReturnPolicy)
        tvMinimumOrderQuantity = findViewById<TextView>(R.id.tvMinimumOrderQuantity)

        tvMetaList =  findViewById<TextView>(R.id.tvMetaList)
        //tvImages = findViewById<ImageView>(R.id.tvImages)
        rvProductImages = findViewById<RecyclerView>(R.id.rvProductImages)
        tvQuantity = findViewById<TextView>(R.id.tvQuantity)

        btnDecrease = findViewById<Button>(R.id.btnDecrease)
        btnIncrease = findViewById<Button>(R.id.btnIncrease)
        btnAddToCart = findViewById<Button>(R.id.btnAddToCart)

        setupQuantityAndCart()


        productList =
            ProductDataHolder.ProductDataHolder.productDetailsList as ArrayList<GetSearchProductDetails>
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

            tvPrice.text = "₹ ${product.price} "
            tvDiscountPercentage.text = "${product.discountPercentage} %"

            tvRating.text = product.rating
            tvStock.text = product.stock

            val tags: List<String> = product.tags
            tvTags.text = tags.joinToString(", ")

            // tvTags.id
            // Log.i("tvTags.id $product.tags.get(1)", "TAGS = $product.tags")
            /* Toast.makeText(
                 this,
                 "TAGS=== $product.tags.get(1)  ",
                 Toast.LENGTH_SHORT
             ).show()*/
            tvBrand.text = product.brand
            tvSku.text = product.sku
            tvWeight.text = product.weight

            val dimensionList: Dimensions = product.dimensions
            Log.i("dimensionList== $dimensionList", "dimensionList = ${product.dimensions}")


            //tvDimensionList.text = dimensionList.joinToString(",")
            tvDimensionList.text =
                "Width: ${dimensionList.width} mm, Height: ${dimensionList.height} mm, Depth: ${dimensionList.depth} mm"

            tvWarrantyInformation.text = product.warrantyInformation
            tvShippingInformation.text = product.shippingInformation
            tvAvailabilityStatus.text = product.availabilityStatus

            val reviewsList = product.reviews
            tvReviewsList.text = reviewsList.joinToString("\n\n") { review ->
                """
                    Rating: ${review.rating}/5
                    Comment: ${review.comment}
                    Date: ${review.date}
                    Reviewer: ${review.reviewerName}
                    """.trimIndent()
            }

            tvReturnPolicy.text = product.returnPolicy
            tvMinimumOrderQuantity.text = product.minimumOrderQuantity

            //tvMetaList
            val metaList = product.meta


            tvMetaList.text = """
                Created At: ${metaList.createdAt}
                
                Updated At: ${metaList.updatedAt}
                
                Barcode: ${metaList.barcode}
                
                QR Code: ${metaList.qrCode}
                """.trimIndent()

            val imagesList: List<String> = product.images

           // tvImages.text = tags.joinToString(", ")



            Log.i(
                "product.images== ${imagesList}","IMAGES "
            )


            val images = product.images

            rvProductImages.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

            rvProductImages.adapter = ProductImageAdapter(images)

            //Load Images

//            Glide.with(tvImages.context)
//                .load(product.images)
//                .into(tvImages)


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