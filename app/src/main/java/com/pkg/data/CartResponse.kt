package com.pkg.data
/**
 * Data class representing the response received from a cart-related network operation.
 */
data class CartResponse(val id: Int,// Unique identifier of the cart
                        val userId: Int,// Unique identifier of the user who owns the cart
                        val products: List<GetAllProductInfo>,// List of products included in the cart response
                        val total: Double,// Total price before discounts
                        val discountedTotal: Double,// Total price after applying discounts
                        val totalProducts: Int,// Total number of unique products in the cart[
                        val totalQuantity: Int)// Total aggregate quantity of items in the cart

