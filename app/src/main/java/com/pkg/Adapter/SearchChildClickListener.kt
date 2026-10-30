package com.pkg.recycler_view

import com.pkg.data.GetAllCartProductsInfo
import com.pkg.data.GetAllProductInfo
import com.pkg.data.GetSearchProductDetails
import com.pkg.data.ProductCategory

interface SearchChildClickListener {
    fun onCartClicked(cartInfo: GetSearchProductDetails, position: Int)
}