package com.pkg.api

import com.pkg.data.CartRequest
import com.pkg.data.CartResponse
import com.pkg.data.DataForProductDetails
import com.pkg.data.GetAllCartResponse
import com.pkg.data.OrderRequest
import com.pkg.data.OrderResponse
import com.pkg.data.Product
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface CartAPI {
   // carts
   //@Headers("Content-Type: application/json")
    @POST("carts/add")
    suspend fun addToCart(
        @Body cart: CartRequest
    ): Response<CartResponse>

    @GET("carts")
    suspend fun getAllCarts(): Response<GetAllCartResponse>

    @POST("c/de1a-3b01-40fc-87eb")
    suspend fun placeOrder(
        @Body request: OrderRequest
    ): Response<OrderResponse>




}