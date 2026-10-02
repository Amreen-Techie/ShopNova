package com.pkg.data

data class GetAllCartResponse(
    val carts : MutableList<CartsInfo>,
    val total: Int,
    val skip: Int,
    val limit: Int,

    )
