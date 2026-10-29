package com.pkg.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pkg.data.GetAllProductInfo
import com.pkg.shopnova.R
import java.util.Locale

class CartProductAdapter(
    private val products: List<GetAllProductInfo>
) : RecyclerView.Adapter<CartProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val ivProduct: ImageView =
            itemView.findViewById(R.id.ivProduct)

        val tvTitle: TextView =
            itemView.findViewById(R.id.tvProductTitle)

        val tvPrice: TextView =
            itemView.findViewById(R.id.tvProductPrice)

        val tvQuantity: TextView =
            itemView.findViewById(R.id.tvProductQuantity)

        val tvDiscount: TextView =
            itemView.findViewById(R.id.tvProductDiscount)

        val tvDiscountedTotal: TextView =
            itemView.findViewById(R.id.tvProductDiscountedTotal)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_product, parent, false)

        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        val product = products[position]

        holder.tvTitle.text = product.title

        holder.tvPrice.text =
            String.format(Locale.US, "Price: %.2f", product.price)

        holder.tvQuantity.text =
            "Quantity: ${product.quantity}"

        holder.tvDiscount.text =
            String.format(
                Locale.US,
                "Discount: %.2f%%",
                product.discountPercentage
            )

        holder.tvDiscountedTotal.text =
            String.format(
                Locale.US,
                "Final price: %.2f",
                product.discountedTotal
            )

        Glide.with(holder.itemView.context)
            .load(product.thumbnail)
            .placeholder(R.drawable.ic_cart)
            .error(R.drawable.cart_image_transparent)
            .into(holder.ivProduct)
    }

    override fun getItemCount(): Int = products.size
}