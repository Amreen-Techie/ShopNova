package com.pkg.data
/**
 * Data class representing the request payload for placing an order.
 */
data class OrderRequest(val cart_id: Int,
                        val payment_method: String)
