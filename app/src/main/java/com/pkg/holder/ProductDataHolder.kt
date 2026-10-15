package com.pkg.holder

import com.pkg.data.GetSearchProductDetails

class ProductDataHolder {
    object ProductDataHolder {
        var productDetailsList = mutableListOf<GetSearchProductDetails>()
    }
}