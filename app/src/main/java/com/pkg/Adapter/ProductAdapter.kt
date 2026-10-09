package com.pkg.recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pkg.data.GetCategoriesData
import com.pkg.data.GetSearchData
import com.pkg.data.GetSearchProductDetails
import com.pkg.shopnova.R


class ProductAdapter(
    var productData: MutableList<GetSearchProductDetails>,
    var productClickListener: CategoryChildClickListener
) : RecyclerView.Adapter<ProductAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        //TODO("Not yet implemented")
        var inflator = LayoutInflater.from((viewGroup.context))
        var childView = inflator.inflate(R.layout.child_product, null)

        return CategoryViewHolder(childView)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        //TODO("Not yet implemented")

        var productTitleName = productData.get(position)
        var productImage = productData.get(position)

        holder.tvProductTitle.setText(productTitleName.title)
        Glide.with(holder.itemView.context)
            .load(productImage.thumbnail)
            .into(holder.tvProductImage)
        //  holder.tvCategoryImage.setImageResource(categoryImage.get(position))

        holder.cl_Main.setOnClickListener { view ->
            Log.i("productTitleName", "Item is clicked $productTitleName")
            productClickListener.onCategoryClicked(position, productTitleName.title)

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

}