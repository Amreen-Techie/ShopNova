package com.pkg.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pkg.data.CartsInfo
import com.pkg.shopnova.R
import java.util.Locale

class CartAdapter(
    private val carts: List<CartsInfo>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvCartId: TextView =
            itemView.findViewById(R.id.tvCartId)

        val tvUserId: TextView =
            itemView.findViewById(R.id.tvUserId)

        val tvProductCount: TextView =
            itemView.findViewById(R.id.tvProductCount)

        val rvCartProducts: RecyclerView =
            itemView.findViewById(R.id.rvCartProducts)

        val tvCartTotal: TextView =
            itemView.findViewById(R.id.tvCartTotal)

        val tvCartDiscountedTotal: TextView =
            itemView.findViewById(R.id.tvCartDiscountedTotal)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart, parent, false)

        return CartViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int
    ) {
        val cart = carts[position]

        holder.tvCartId.text = "Cart ID: ${cart.id}"
        holder.tvUserId.text = "User ID: ${cart.userId}"

        holder.tvProductCount.text =
            "Products: ${cart.totalProducts} | Quantity: ${cart.totalQuantity}"

        holder.tvCartTotal.text = String.format(
            Locale.US,
            "Total: %.2f",
            cart.total
        )

        holder.tvCartDiscountedTotal.text = String.format(
            Locale.US,
            "Discounted Total: %.2f",
            cart.discountedTotal
        )

        holder.rvCartProducts.layoutManager =
            LinearLayoutManager(holder.itemView.context)

        holder.rvCartProducts.isNestedScrollingEnabled = false

        holder.rvCartProducts.adapter =
            CartProductAdapter(cart.products)
    }

    override fun getItemCount(): Int = carts.size
}