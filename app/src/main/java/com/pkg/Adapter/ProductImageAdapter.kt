package com.pkg.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pkg.shopnova.databinding.ItemProductImageBinding

class ProductImageAdapter(
    private val images: List<String>
) : RecyclerView.Adapter<ProductImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(val binding: ItemProductImageBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        val binding = ItemProductImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ImageViewHolder,
        position: Int
    ) {
        Glide.with(holder.itemView.context)
            .load(images[position])
            .placeholder(android.R.drawable.ic_menu_gallery)
            .into(holder.binding.ivProductImage)
    }

    override fun getItemCount(): Int = images.size
}