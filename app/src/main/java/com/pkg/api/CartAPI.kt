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
/**
 * Retrofit API interface for cart and order-related operations.
 *
 * Each function is a suspend function, so it can be called from a coroutine.
 */
interface CartAPI {
    /**
     * Sends a request to add a product or products to the cart.
     *
     * @param cart Request body containing the cart details.
     * @return The HTTP response containing the added cart details.
     */

   //@Headers("Content-Type: application/json")
    @POST("carts/add")
    suspend fun addToCart(
        @Body cart: CartRequest
    ): Response<CartResponse>


    /**
     * Retrieves the list of all carts.
     *
     * @return The HTTP response containing the cart list.
     */
    @GET("carts")
    suspend fun getAllCarts(): Response<GetAllCartResponse>

    /**
     * Submits an order using the provided order details.
     *
     * @param request Request body containing the order information.
     * @return The HTTP response containing the order result.
     */
    @POST("c/de1a-3b01-40fc-87eb")
    suspend fun placeOrder(
        @Body request: OrderRequest
    ): Response<OrderResponse>




}