package com.pkg.shopnova

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pkg.Adapter.ProductImageAdapter
import com.pkg.data.CartRequest
import com.pkg.data.CartResponse
import com.pkg.data.Dimensions
import com.pkg.data.GetSearchProductDetails
import com.pkg.data.LoginRequest
import com.pkg.data.OrderRequest
import com.pkg.data.OrderResponse
import com.pkg.data.ProductAddCartRequest
import com.pkg.data.Review
import com.pkg.holder.ProductDataHolder
import com.pkg.retrofit.AppRetroClient
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
//import javax.security.auth.callback.Callback
import kotlin.text.get
import kotlin.toString
import retrofit2.Callback


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
    var token : String = ""



    private var quantity = 1

    lateinit var tvQuantity: TextView
    lateinit var btnDecrease: Button
    lateinit var btnIncrease: Button
    lateinit var btnAddToCart: Button
    lateinit var btnPlaceOrder: Button

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
        btnPlaceOrder = findViewById<Button>(R.id.btnPlaceOrder)
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


            println("btnAddToCart  Clicked!")
            lifecycleScope.launch {
                try {

                    val quantity = tvQuantity.text.toString().toIntOrNull() ?: 1
                    val product = productList[position]

                    val requestAddtoCart = CartRequest(
                        userId = 1,
                        products = listOf(
                            ProductAddCartRequest(
                                id = product.id.toInt(),
                                quantity = quantity
                            )
                        )
                    )

                        println("CHECK 1: $requestAddtoCart")

                        val addtoCartResponse = AppRetroClient.getApiCart.addToCart(requestAddtoCart)
                        println("CHECK 2: $addtoCartResponse")

                        if (addtoCartResponse.isSuccessful) {

                            val data = addtoCartResponse.body()

                            if (data != null) {

                                val totalQuantity = data.totalQuantity

                                /*Toast.makeText(
                                    this@ProductDetailsActivity,
                                    "totalQuantity: $totalQuantity",
                                    Toast.LENGTH_LONG
                                ).show()*/
                                Toast.makeText(this@ProductDetailsActivity, "${quantity} Product Added to Cart!", Toast.LENGTH_SHORT).show()

                                Log.i(
                                    "AddToCart","data.id = ${data.id}"
                                )
                                Log.i(
                                    "AddToCart","data.totalProducts = ${data.totalProducts}"
                                )
                                Log.i(
                                    "AddToCart","data.userId = ${data.userId}"
                                )
                                Log.i(
                                    "AddToCart","data.discountedTotal = ${data.discountedTotal}"
                                )
                                Log.i(
                                    "AddToCart","data.id = ${data.products.get(position).discountedTotal}"
                                )

                            }
                        } else {
                            println("Error addtoCartResponse: ${addtoCartResponse.code()}")
                        }



                }
                catch (e: Exception) {
                    println("AddTOCart Exception: ${e.message}")
                }

            }

        }
        btnPlaceOrder.setOnClickListener {
            lifecycleScope.launch {

            try {
                placeOrder()
        }
        catch (e: Exception) {
            println("btnPlaceOrder Exception: ${e.message}")
        }
        }
}

        }

    private suspend fun placeOrder() {

        val request = OrderRequest(
            cart_id = 100,
            payment_method = "cash on delivery"
        )
        val placeOrderResponse = AppRetroClient.getApiCart.placeOrder(request)
        println("CHECK 2: $placeOrderResponse")

        if (placeOrderResponse.isSuccessful) {

            val data = placeOrderResponse.body()

            if (data != null) {

                val message = data.message
                val success = data.success

                Toast.makeText(
                    this@ProductDetailsActivity,
                    "$message",
                    Toast.LENGTH_LONG
                ).show()
                Log.i(
                    "message","data.id = ${success}"
                )

            }
        } else {
            println("Error placeOrder: ${placeOrderResponse.code()}")
        }



    }
}