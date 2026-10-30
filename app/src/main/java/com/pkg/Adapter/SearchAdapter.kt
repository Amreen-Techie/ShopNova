package com.pkg.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pkg.data.GetSearchProductDetails
import com.pkg.shopnova.R
import java.util.Locale

class SearchAdapter(
    private val products: List<GetSearchProductDetails>
) : RecyclerView.Adapter<SearchAdapter.CartViewHolder>() {

    class CartViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView =
            itemView.findViewById(R.id.tvTitle)

        val tvDescription: TextView =
            itemView.findViewById(R.id.tvDescription)

        val tvCategory: TextView =
            itemView.findViewById(R.id.tvCategory)

        val tvPrice: TextView =
            itemView.findViewById(R.id.tvPrice)

        val tvSearchDiscountedPercentage: TextView =
            itemView.findViewById(R.id.tvSearchDiscountedPercentage)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search, parent, false)

        return CartViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CartViewHolder,
        position: Int
    ) {
        val product = products[position]

        holder.tvTitle.text = "Title: ${product.title}"
        holder.tvDescription.text = "Description: ${product.description}"
        holder.tvCategory.text = "Category: ${product.category}"

        holder.tvPrice.text = String.format(
            Locale.US,
            "Price: %.2f",
            product.price
        )

        holder.tvSearchDiscountedPercentage.text = String.format(
            Locale.US,
            "Discounted Per: %.2f",
            product.discountPercentage
        )
    }

    override fun getItemCount(): Int = products.size
}
