package com.pkg.recycler_view

import com.pkg.data.GetAllCartProductsInfo
import com.pkg.data.GetAllProductInfo
import com.pkg.data.GetSearchProductDetails
import com.pkg.data.ProductCategory
/**
 * Click listener interface for handling search result item selections.
 */
interface SearchChildClickListener {
    /**
     * Called when a search result item is clicked.
     */
    fun onCartClicked(cartInfo: GetSearchProductDetails, position: Int)
}