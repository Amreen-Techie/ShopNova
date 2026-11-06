package com.pkg.data
/**
 * Data class representing the payload structure required to make a cart request (e.g., adding items to a cart).
 */
data class CartRequest(
    val userId: Int, // Unique identifier of the user making the request
    val products: List<ProductAddCartRequest> // List of products to be added to the cart
)
