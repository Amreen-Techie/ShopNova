package com.pkg.recycler_view

import com.pkg.data.ProductCategory
/**
 * Click listener interface for handling interactions with category items.
 */
interface CategoryChildClickListener {
    /**
     * Called when a category item is clicked.
     */
    fun onCategoryClicked(category: ProductCategory, position: Int)
}