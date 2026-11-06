package com.pkg.recycler_view

import com.pkg.data.GetAllProductInfo
import com.pkg.data.ProductCategory
/**
 * Click listener interface for handling interactions with individual products inside a cart.
 */
interface CartChildClickListener {
    /**
     * Called when a cart product item is clicked.
     */
    fun onCartClicked(cartInfo: GetAllProductInfo, position: Int)
}