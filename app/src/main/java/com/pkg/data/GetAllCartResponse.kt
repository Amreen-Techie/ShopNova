package com.pkg.data
/**
 * Data class representing the paginated response containing multiple carts
 */
data class GetAllCartResponse(
    val carts : MutableList<CartsInfo>,// List of cart items
    val total: Int,// Total number of carts available
    val skip: Int,// Number of items skipped for pagination
    val limit: Int,// Maximum number of items returned per page

    )
