package com.pkg.data

data class CartRequest(
    val userId: Int,
    val products: List<ProductAddCartRequest>
)
