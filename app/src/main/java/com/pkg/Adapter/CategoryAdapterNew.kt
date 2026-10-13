package com.pkg.recycler_view

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.pkg.data.ProductCategory
import com.pkg.shopnova.R


class CategoryAdapterNew(var categoryList: MutableList<ProductCategory>, var categoryClickListener : CategoryChildClickListener) : RecyclerView.Adapter<CategoryAdapterNew.CategoryViewHolder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int

    ): CategoryViewHolder {
        //TODO("Not yet implemented")
        context = viewGroup.context

        var inflator = LayoutInflater.from((viewGroup.context))
        var childView = inflator.inflate(R.layout.child_category, null)

        return CategoryViewHolder(childView)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        //TODO("Not yet implemented")

        var newsTitleName = categoryList.get(position)
        var categoryImage = categoryList.get(position)

        //    holder.tvCategoryTitle.text = newsTitleName.name


        //if (position == 0) {
            holder.cl_Main.setOnClickListener { view ->
                Log.i("CategoryAdapter", "CategoryAdapter Item is clicked $newsTitleName")
                categoryClickListener.onCategoryClicked(categoryList,position )
                holder.tvCategoryTitle.text = newsTitleName.name
            }

      /*
        } else {
            newsTitleName.name


            Toast.makeText(context, "Other category items clicked", Toast.LENGTH_SHORT).show()
        }*/

    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")

        return categoryList.size
    }


    class CategoryViewHolder(childView: View) : RecyclerView.ViewHolder(childView) {
        var tvCategoryTitle: TextView
        var cl_Main: ConstraintLayout

        init {

            tvCategoryTitle = childView.findViewById<TextView>(R.id.tv_item_title)
            cl_Main = childView.findViewById<ConstraintLayout>(R.id.main)

        }

    }
}



/*
class CategoryAdapter(
    private val categoryList: List<GetCategoriesData>,
    private val listener: CategoryClickListener
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(
        val binding: ItemCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        val category = categoryList[position]

        // Set your category name
        holder.binding.tvCategoryName.text = category.categoryName

        holder.itemView.setOnClickListener {
            listener.onCategoryClick(category.categoryId)
        }
    }

    override fun getItemCount(): Int = categoryList.size
}*/
