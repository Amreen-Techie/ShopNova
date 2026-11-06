package com.pkg.data

import kotlin.collections.mutableListOf
/**
 * Data class representing comprehensive information about products added to a cart,
 * including total pricing, quantities, user identification, and the list of items[cite: 6].
 */
data class AddCartProductInfo(
    val id: Int, // Unique identifier for the cart.
    val total: Double, // Total price before discounts.
    val discountedTotal: Int, // Total price after applying discounts.
    val userId: Int, // Identifier of the user who owns the cart.
    val totalProducts: Int, // Total number of unique products in the cart.
    val totalQuantity: Int, // Total aggregate quantity of all items in the cart.
    val productList : MutableList<CartProductInfo> // Mutable list containing detailed information for each product in the cart.

)
