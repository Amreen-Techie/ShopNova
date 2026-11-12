package com.pkg.api

import com.pkg.data.ProductCategory
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit API interface for handling product category-related network requests.
 */

interface CategoriesAPI {
    /**
     * Fetches the list of all available product categories from the server.
     * @return A Retrofit [Response] wrapping a list of [ProductCategory] objects.
     */
    @GET("products/categories")
    suspend fun getCategories(): Response<List<ProductCategory>>
}