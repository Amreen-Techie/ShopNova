package com.pkg.data
/**
 * Data class representing details of a product inside a cart.
 */
data class GetAllProductInfo( var id : Int,
                              var title : String,
                              var price : Double,
                              var quantity : Int,
                              var total : Double,
                              var discountPercentage : Double,
                              var discountedTotal : Double,
                              var thumbnail : String,)
