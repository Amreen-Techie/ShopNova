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
import com.pkg.shopnova.R


class CategoryAdapter(var categoryData: MutableList<GetCategoriesData>, var categoryClickListener : CategoryChildClickListener) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
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

        var newsTitleName = categoryData.get(position)
        var categoryImage = categoryData.get(position)

        holder.tvCategoryTitle.text = newsTitleName.name
       // holder.tvCategorySubTitle.text = categoryImage.slug

      //  holder.tvCategoryImage.setImageResource(categoryImage.get(position))

        holder.cl_Main.setOnClickListener { view->
            Log.i("NewsAdapter", "Item is clicked $newsTitleName")
            categoryClickListener.onCategoryClicked(position,newsTitleName.name)

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