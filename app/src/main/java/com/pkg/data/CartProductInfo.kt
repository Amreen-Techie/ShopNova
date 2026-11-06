package com.pkg.data


/*
 Data class representing detailed information about a specific product within a cart
 */
data class CartProductInfo(

    var productId : Int, //Unique identifier for the product
    var title : String, // Title or name of the product
    var price : Double, // Unit price of the product
    var quantity : Int, // Quantity of the product in the cart
    var total : Double, // Total price for this product line item before discount
    var discountPercentage : Double, // Discount percentage applied to the product
    var discountedPrice : Int, // Final price after discount
    var thumbnail : String, // URL or path for the product thumbnail image

)
