package com.pkg.shopnova

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.pkg.api.JSONParser
import com.pkg.data.CartsInfo
import com.pkg.data.GetAllProductInfo
import com.pkg.data.ProductCategory
import com.pkg.recycler_view.CartAdapter
import com.pkg.recycler_view.CartChildClickListener
import com.pkg.recycler_view.CategoryChildClickListener
import com.pkg.retrofit.AppRetroClient
import com.pkg.shopnova.databinding.ActivityCartRvBinding
import kotlinx.coroutines.launch
import org.json.JSONObject

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartRvBinding
  //  private var cartList = MutableList<GetAllProductInfo>()
    private var cartList: MutableList<CartsInfo> = mutableListOf()
    lateinit var cartAdapter: CartAdapter

    lateinit var rvCart: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartRvBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_cart_rv)
        GetAllCarts()

    }
    private fun GetAllCarts()
    {

            try
            {

                lifecycleScope.launch {

                    binding.pb.visibility = View.VISIBLE
                    val response = AppRetroClient.getApiCart.getAllCarts()

                    if (response.isSuccessful) {

                        val jsonString = Gson().toJson(response.body())
                        println("jsonString = ${jsonString}")

                        var jsonParser = JSONParser()
                        //var jsonObj = JSONObject(jsonString)
                       // var getAllCartResponse = jsonParser.parseGetAllCartsResponse(jsonString)

                      //  println("getAllCartResponse = ${getAllCartResponse.carts.toString()}")

                        cartList = jsonParser.parseGetAllCartsResponse(jsonString) as MutableList<CartsInfo>

                        cartAdapter = CartAdapter(
                            cartList,this@CartActivity,cartClickListener)

                        rvCart.layoutManager = LinearLayoutManager(this@CartActivity,LinearLayoutManager.HORIZONTAL,false)
                        rvCart.adapter = cartAdapter

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
    private val cartClickListener = object : CartChildClickListener {

        fun onCategoryClicked(
            data: ProductCategory,
            position: Int
        ) {
            // Receive clicked category here

            Log.i("CartActivity", "Item is clicked $position")


            val selectedCartId = cartList.get(position).id
            // Load products for selected category
           /* if (position!=0)
            {*/

                println("Adapter Enters in if selectedCartId: $selectedCartId")
                GetAllCarts()



            val toolbar = findViewById<Toolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)

            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        override fun onCartClicked(cartInfo: GetAllProductInfo, position: Int) {
            TODO("Not yet implemented")
        }
    }
}