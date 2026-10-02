package com.pkg.data

data class CartResponse(val id: Int,
                        val userId: Int,
                        val products: List<Product>,
                        val total: Double,
                        val discountedTotal: Double,
                        val totalProducts: Int,
                        val totalQuantity: Int)
