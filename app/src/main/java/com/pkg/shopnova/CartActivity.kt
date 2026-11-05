package com.pkg.shopnova

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.pkg.Adapter.CartAdapter
import com.pkg.api.JSONParser
import com.pkg.data.CartsInfo
import com.pkg.data.GetAllProductInfo
import com.pkg.data.ProductCategory
import com.pkg.recycler_view.CartChildClickListener
import com.pkg.retrofit.AppRetroClient
import com.pkg.shopnova.databinding.ActivityCartBinding
import kotlinx.coroutines.launch
import org.json.JSONObject
/**
 * Activity responsible for managing and displaying the user's shopping cart items
 * using a vertical RecyclerView and fetching cart data asynchronously via Retrofit.
 */

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    // Mutable list holding cart information entries.
    private var cartList: MutableList<CartsInfo> = mutableListOf()
    lateinit var cartAdapter: CartAdapter

    // RecyclerView for displaying list of carts.
    lateinit var rvCart: RecyclerView

    private lateinit var rvCarts: RecyclerView
   // private lateinit var cartAdapterNew: CartAdapterNew


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout using View Binding.
        binding = ActivityCartBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Initialize cart RecyclerView reference.
        rvCart = findViewById<RecyclerView>(R.id.rv_cart)

        // Fetch all user carts when the activity is created[cite: 2]
        GetAllCarts()

    }

    /**
     * Fetches all shopping carts from the backend API using coroutines
     * and binds the data to the cart RecyclerView adapter.
     */

    private fun GetAllCarts()
    {

            try
            {

                lifecycleScope.launch {
                    // Show progress bar indicator.
                    binding.pb.visibility = View.VISIBLE

                    // Make API network call to get all carts.
                    val response = AppRetroClient.getApiCart.getAllCarts()

                    if (response.isSuccessful) {

                        val jsonString = Gson().toJson(response.body())
                        println("jsonString = ${jsonString}")

                        var jsonParser = JSONParser()
                        var jsonObj = JSONObject(jsonString)

                        // Parse JSON response into structured cart objects.
                        var getAllCartResponse = jsonParser.parseGetAllCartsResponse(jsonString)
                        println("getAllCartResponse = ${getAllCartResponse}")


                        cartList = getAllCartResponse.carts.toMutableList()

                        // Initialize adapter and configure vertical LinearLayoutManager.
                        cartAdapter = CartAdapter(
                            cartList)

                        rvCart.layoutManager = LinearLayoutManager(this@CartActivity,LinearLayoutManager.VERTICAL,false)
                        rvCart.adapter = cartAdapter


                    } else {
                        println("Error: ${response.code()}")
                    }
                    // Hide progress bar indicator.
                    binding.pb.visibility = View.GONE
                }


            }
            catch (e: Exception) {
                println("Error: ${e.message}")
            }
    }

    /**
     * Click listener interface implementation for handling interactions with cart items.
     */
    private val cartClickListener = object : CartChildClickListener {

        @SuppressLint("SuspiciousIndentation")
        fun onCategoryClicked(
            data: ProductCategory,
            position: Int
        ) {
            // Receive clicked category here

            Log.i("CartActivity", "Item is clicked $position")


            val selectedCartId = cartList.get(position).id

                println("Adapter Enters in if selectedCartId: $selectedCartId")

            // Reload cart data on click action.

            GetAllCarts()
            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)

            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        override fun onCartClicked(cartInfo: GetAllProductInfo, position: Int) {
            // Handle individual cart item selection event.

        }
    }
}