package com.pkg.shopnova

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pkg.Adapter.CartAdapterNew
import com.pkg.data.CartResponse
import com.pkg.recycler_view.CartAdapter

class CartActivityNew : AppCompatActivity() {

    private lateinit var rvCarts: RecyclerView
    private lateinit var cartAdapter: CartAdapterNew

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        rvCarts = findViewById(R.id.rv_cart)

        rvCarts.layoutManager = LinearLayoutManager(this)

        // Call this function when your API returns CartResponse.
        // Example:
        // displayCarts(cartResponse)
    }

    private fun displayCarts(response: CartResponse) {
     //   cartAdapter = CartAdapterNew(response.carts)
        rvCarts.adapter = cartAdapter
    }
}
