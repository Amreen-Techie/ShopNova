package com.pkg.data

data class CartProductInfo(
    var productId : Int,
    var title : String,
    var price : Double,
    var quantity : Int,
    var total : Double,
    var discountPercentage : Double,
    var discountedPrice : Int,
    var thumbnail : String,

)
