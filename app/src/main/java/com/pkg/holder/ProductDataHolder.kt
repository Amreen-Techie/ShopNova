package com.pkg.holder

import com.pkg.data.GetSearchProductDetails

/**
 * A singleton data holder class used to store and share a list of product details
 * across different components or activities within the application.
 */
class ProductDataHolder {
    object ProductDataHolder {
        var productDetailsList = mutableListOf<GetSearchProductDetails>()
    }
}