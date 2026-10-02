package com.pkg.data

data class CartsInfo(
    val id: Int,
    val products : MutableList<GetAllProductInfo>,
    val total: Double,
    val discountedTotal: Double,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int
)
