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

      //  holder.tvCategoryImage.setImageResource(categoryImage.get(position))

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
    class CategoryViewHolder(childView: View) : RecyclerView.ViewHolder(childView) {
        var tvCategoryTitle: TextView


      //  var tvCategorySubTitle: TextView
     //   var tvCategoryImage: ImageView
        var cl_Main : ConstraintLayout
        init {
            tvCategoryTitle = childView.findViewById<TextView>(R.id.tv_item_title)
          //  tvCategoryImage = childView.findViewById<ImageView>(R.id.imageCategory)
          //  tvCategorySubTitle =  childView.findViewById<TextView>(R.id.tv_item_subtitle)

            cl_Main = childView.findViewById<ConstraintLayout>(R.id.main)
/*            val imageUrln = "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp\n"
            val imageUrl: String = imageUrln
            Glide.with(this)
                .load(imageUrl)
                .into(tvCategoryImage)
        */}
    }

}