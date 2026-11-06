package com.pkg.data
/**
 * Data class representing detailed summary information about a user's cart.
 */
data class CartsInfo(
    val id: Int,// Unique identifier of the cart
    val products : MutableList<GetAllProductInfo>,//Mutable list of products contained in the cart
    val total: Double,// Total price before discounts
    val discountedTotal: Double,//Total price after discounts
    val userId: Int,// Unique identifier of the user
    val totalProducts: Double,//Total number of products in the cart represented as a double
    val totalQuantity: Int //Total quantity of items in the cart
)
