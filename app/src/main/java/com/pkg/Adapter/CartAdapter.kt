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
import com.pkg.data.CartsInfo
import com.pkg.data.GetAllProductInfo
import com.pkg.shopnova.R


class CartAdapter(
    var cartList: MutableList<CartsInfo>, private val activityContext: Context
    , var cartClickListener: CartChildClickListener

) : RecyclerView.Adapter<CartAdapter.CategoryViewHolder>() {

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
        var childView = inflator.inflate(R.layout.child_cart, null)

        return CategoryViewHolder(childView)
    }

    override fun onBindViewHolder( holder: CategoryViewHolder,
        position: Int) {
        //TODO("Not yet implemented")

        var cartId = cartList.get(position)
        var categoryImage = cartList.get(position)

        holder.tvCategoryTitle.text = cartId.id.toString()

      //  holder.tvCategoryImage.setImageResource(categoryImage.get(position))

        holder.cl_Main.setOnClickListener { view->
            Log.i("CartAdapter", "Item is clicked $cartId")
            //cartClickListener.onCartClicked(cartId,position)
            Toast.makeText(
                context,
                "Test Cart",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return cartList.size
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