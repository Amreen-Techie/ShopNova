package com.pkg.recycler_view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.pkg.data.ProductCategory
import com.pkg.shopnova.R

/**
 * RecyclerView Adapter for displaying product categories.
 */
class CategoryAdapter(

    var categoryData: MutableList<ProductCategory>, private val activityContext: Context
    , var categoryClickListener: CategoryChildClickListener,
    var toolbarn: androidx.appcompat.widget.Toolbar
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private lateinit var context: Context

    init {
        context = activityContext
    }
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int

    ): CategoryViewHolder {
        //TODO("Not yet implemented")
        var inflator = LayoutInflater.from((viewGroup.context))
        var childView = inflator.inflate(R.layout.child_category, null)

        return CategoryViewHolder(childView)
    }

    override fun onBindViewHolder( holder: CategoryViewHolder,
        position: Int) {
        //TODO("Not yet implemented")

        var category = categoryData.get(position)
        var categoryImage = categoryData.get(position)

        holder.tvCategoryTitle.text = category.name
       // holder.tvCategorySubTitle.text = categoryImage.slug
        toolbarn.title = "Shop Nova Test"

// Handle click events on category items
        holder.cl_Main.setOnClickListener { view->
            Log.i("CategoryAdapter", "Item is clicked $category")
            categoryClickListener.onCategoryClicked(category,position)
            Toast.makeText(
                context,
                "Test",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return categoryData.size
    }
    /**
     * ViewHolder class for category list items.
     */
    class CategoryViewHolder(childView: View) : RecyclerView.ViewHolder(childView) {
        var tvCategoryTitle: TextView
        var cl_Main : ConstraintLayout
        init {
            tvCategoryTitle = childView.findViewById<TextView>(R.id.tv_item_title)

            cl_Main = childView.findViewById<ConstraintLayout>(R.id.main)
}
    }

}