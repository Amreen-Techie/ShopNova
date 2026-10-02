package com.pkg.shopnova

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.pkg.JSONParser
import com.pkg.data.CartRequest
import com.pkg.data.DataForProductDetails
import com.pkg.data.GetCategoriesData
import com.pkg.data.LoginRequest
import com.pkg.data.Meta
import com.pkg.data.OrderRequest
import com.pkg.data.OrderResponse
import com.pkg.data.Product
import com.pkg.data.ProductAddCartRequest
import com.pkg.data.ProductDimension
import com.pkg.data.GetSearchData

import com.pkg.data.ProductInfo
import com.pkg.data.Review
import com.pkg.retrofit.AppRetroClient
import com.pkg.shopnova.databinding.ActivityMainBinding
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        LoginMethod()
        GetCategories()
        GetProductByCategories()
        SearchProduct()
        GetAllProducts()
        GetAllCarts()
        AddCartsPost()
        placeOrderMethod()
    }
    private fun LoginMethod()
    {

      /*  binding.btnLogin.setOnClickListener {
            lifecycleScope.launch {

            }
        }*/

    }

    private fun GetCategories()
    {
        binding.btnGetCategories.setOnClickListener {

            lifecycleScope.launch {

                val response = AppRetroClient.getApiCategory.getCategories()
                var getCategoryList : List<GetCategoriesData> = emptyList()
                if (response.isSuccessful) {
                    val jsonString = Gson().toJson(response.body())
                    println("GetCategories  = ${jsonString}")

                    var jsonParser = JSONParser()

                    getCategoryList = jsonParser.parseGetCategories(jsonString.toString())

                    println("getCategoryList  = ${getCategoryList}")

                } else {

                    println("Error: ${response.code()}")
                }
                getCategoryList.forEach { category ->
                    println("name = ${category.name}")
                    println(category.slug)
                    println(category.url)
                }
            }
        }

        }
    private fun  GetProductByCategories()
    {
        binding.btnGetProductByCategories.setOnClickListener {
            try
            {
                lifecycleScope.launch {
                    binding.pb.visibility = View.VISIBLE
                    val response = AppRetroClient.getApiProduct.getProductByCategories()

                    if (response.isSuccessful) {
                        val jsonString = Gson().toJson(response.body())
                        println("GetProductByCategories  = ${jsonString}")

                        var jsonParser = JSONParser()
                        var jsonObj = JSONObject(jsonString)
                        println("jsonString  = ${jsonString}")

                        var getSearchResponse = jsonParser.parseSearchProducts(jsonString)

                        println("GetProductByCategories  = ${getSearchResponse}")
                    } else {
                        println("Error GetProductByCategories: ${response.code()}")
                    }
                    binding.pb.visibility = View.GONE
                }
            }
            catch (e: Exception) {
                println("Error: ${e.message}")
            }
            }
    }


    @SuppressLint("SuspiciousIndentation")
    private fun GetAllProducts()
    {
        binding.btnGetAllProducts.setOnClickListener {

            try
            {
                lifecycleScope.launch {
                    binding.pb.visibility = View.VISIBLE

                    val response = AppRetroClient.getApiProduct.getAllProduct()

                        if (response.isSuccessful) {
                            val jsonString = Gson().toJson(response.body())
                            println("btnGetAllProducts  = ${jsonString}")

                            var jsonParser = JSONParser()
                            var jsonObj = JSONObject(jsonString)
                            println("jsonString  = ${jsonString}")

                            var getSearchResponse = jsonParser.parseSearchProducts(jsonString)

                            println("btnGetAllProducts  = ${getSearchResponse}")
                    } else {
                        println("Error btnGetAllProducts: ${response.code()}")
                    }
                    binding.pb.visibility = View.GONE
            }
            }
            catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }



    private fun GetAllCarts()
    {
        binding.btnGetAllCarts.setOnClickListener {

            try
            {

                lifecycleScope.launch {

                    binding.pb.visibility = View.VISIBLE
                    val response = AppRetroClient.getApiCart.getAllCarts()

                    if (response.isSuccessful) {

                        val jsonString = Gson().toJson(response.body())
                        println("jsonString = ${jsonString}")

                        var jsonParser = JSONParser()
                        var jsonObj = JSONObject(jsonString)
                        var getAllCartResponse = jsonParser.parseGetAllCartsResponse(jsonString)

                        println("getAllCartResponse = ${getAllCartResponse}")

                    } else {
                        println("Error: ${response.code()}")
                    }
                    binding.pb.visibility = View.GONE
                }


            }
            catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    private fun  SearchProduct()
    {
        binding.btnSearchProduct.setOnClickListener {

            try
            {

                lifecycleScope.launch {
                    binding.pb.visibility = View.VISIBLE
                    val response = AppRetroClient.getApiProduct.searchProduct("tablet")
                    println("response getSearchResponse = ${response}")

                    if (response.isSuccessful) {
                        val jsonString = Gson().toJson(response.body())
                        println("SearchProduct getSearchResponse = ${jsonString}")

                        var jsonParser = JSONParser()
                        var jsonObj = JSONObject(jsonString)
                        println("jsonString  = ${jsonString}")

                        var getSearchResponse = jsonParser.parseSearchProducts(jsonString)

                        println("SearchProduct getSearchResponse = ${getSearchResponse}")

                        println(getSearchResponse.products)   // 15.05


                    } else {
                        println("Error getSearchResponse: ${response.code()}")
                    }
                    binding.pb.visibility = View.GONE
                }


            }
            catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    private fun AddCartsPost()
    {
        binding.btnAddCart.setOnClickListener {

            try {
                lifecycleScope.launch {
                    binding.pb.visibility = View.VISIBLE
                    val cartRequest = CartRequest(
                        userId = 1,
                        products = listOf(
                            ProductAddCartRequest(id = 144, quantity = 35),
                            ProductAddCartRequest(id = 98, quantity = 4)
                        )
                    )

                    val response = AppRetroClient.getApiCart.addToCart(cartRequest)

                    if (response.isSuccessful) {

                        val jsonString = Gson().toJson(response.body())
                        var jsonParser = JSONParser()
                        var jsonObj = JSONObject(jsonString)
                        var addToCartResponse = jsonParser.parseAddCartResponse(jsonString)

                       println("addToCartResponse = ${addToCartResponse}")

                    }

                }
                binding.pb.visibility = View.GONE


            }
            catch (e: Exception) {
                println("ERROR@@@@@@@@@@@@@@: ${e.message}")
            }
        }
    }
    private fun placeOrderMethod()
    {

        binding.btnPlaceOrder.setOnClickListener {
            lifecycleScope.launch {

                try {
                    val order_request = OrderRequest(
                        cart_id = 100,
                        payment_method = "cash on delivery"
                    )
                    val response = AppRetroClient.getApiCart.placeOrder(order_request)

                    if (response.isSuccessful) {

                        val data = response.body()
                        Log.v("API", "Success: ${data?.success}")
                        Log.v("API", "Message: ${data?.message}")
                        val order = OrderResponse( success = data?.success ?: false,
                            message = data?.message ?: "")
                        Log.v("order", "Success:@@@@@@@@@@@@@ ${order.success}")
                        Log.v("order", "message:@@@@@@@@@@@@@ ${order.message}")

                        } else {
                        Log.e("API", "Error: ${response.code()}")
                    }


                }

                catch (e: Exception) {
                    Log.e("API", e.message ?: "API error")
                }


            }


        }


    }

}