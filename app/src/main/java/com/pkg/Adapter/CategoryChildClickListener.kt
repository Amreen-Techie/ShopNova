package com.pkg.recycler_view

import com.pkg.data.ProductCategory

interface CategoryChildClickListener {
    fun onCategoryClicked(data: MutableList<ProductCategory>, position: Int)
}