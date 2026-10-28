package com.pkg.recycler_view

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pkg.data.GetSearchData
import com.pkg.data.GetSearchProductDetails
import com.pkg.holder.ProductDataHolder
import com.pkg.shopnova.ProductDetailsActivity
import com.pkg.shopnova.R


class ProductAdapter(var productData: MutableList<GetSearchProductDetails>,
                     var productClickListener: CategoryChildClickListener
) : RecyclerView.Adapter<ProductAdapter.CategoryViewHolder>() {

    private lateinit var contextV : Context
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int

    ): CategoryViewHolder {
        //TODO("Not yet implemented")
        var inflator = LayoutInflater.from((viewGroup.context))
        var childView = inflator.inflate(R.layout.child_product, null)
         contextV = viewGroup.context // Get context directly from parent

        return CategoryViewHolder(childView)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        //TODO("Not yet implemented")
        var product = productData.get(position)
        //var productImage = productData.get(position)

        holder.tvProductTitle.setText(product.title)
        Glide.with(holder.itemView.context)
            .load(product.thumbnail)
            .into(holder.tvProductImage)
        //  holder.tvCategoryImage.setImageResource(categoryImage.get(position))

        holder.cl_Main.setOnClickListener { view ->
            Log.i("productTitleName", "product Item is clicked $product")
            //productClickListener.onCategoryClicked( productTitleName.title,position)
        //Product details page visible when product item is clicked.
            val intent = Intent(contextV, ProductDetailsActivity::class.java)
            ProductDataHolder.ProductDataHolder.productDetailsList = productData
            intent.putExtra("Position",position)
            contextV.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return productData.size
    }


    class CategoryViewHolder(childView: View) : RecyclerView.ViewHolder(childView) {
        var tvProductTitle: TextView

           var tvProductImage: ImageView
        var cl_Main: ConstraintLayout

        init {
            tvProductTitle = childView.findViewById<TextView>(R.id.tv_item_title)
            tvProductImage = childView.findViewById<ImageView>(R.id.imageCategory)
            cl_Main = childView.findViewById<ConstraintLayout>(R.id.main)

        }
    }
  /*  fun updateProducts(newProducts: List<GetSearchData>) {
        productData.clear()
        productData.addAll(newProducts)
        notifyDataSetChanged()
    }*/
}

private fun MutableList<GetSearchProductDetails>.addAll(elements: List<GetSearchData>) {}
