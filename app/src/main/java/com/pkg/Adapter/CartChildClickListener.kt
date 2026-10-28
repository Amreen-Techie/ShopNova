package com.pkg.recycler_view

import com.pkg.data.GetAllProductInfo
import com.pkg.data.ProductCategory

interface CartChildClickListener {
    fun onCartClicked(cartInfo: GetAllProductInfo, position: Int)
}