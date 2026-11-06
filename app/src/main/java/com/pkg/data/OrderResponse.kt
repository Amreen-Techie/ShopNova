package com.pkg.data
/**
 * Data class representing the response received after attempting to place an order.
 */
data class OrderResponse(val success: Boolean,
                         val message: String)
