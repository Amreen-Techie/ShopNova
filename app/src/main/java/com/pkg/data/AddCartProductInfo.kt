package com.pkg.data

import kotlin.collections.mutableListOf

data class AddCartProductInfo(
    val id: Int,
    val total: Double,
    val discountedTotal: Int,
    val userId: Int,
    val totalProducts: Int,
    val totalQuantity: Int,
    val productList : MutableList<CartProductInfo>
    )
