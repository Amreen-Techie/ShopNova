package com.pkg.recycler_view

import com.pkg.data.ProductCategory

interface CategoryChildClickListener {
    fun onCategoryClicked(category: ProductCategory, position: Int)
}