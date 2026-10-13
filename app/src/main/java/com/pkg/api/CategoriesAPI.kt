package com.pkg.api

import com.pkg.data.ProductCategory
import retrofit2.Response
import retrofit2.http.GET

interface CategoriesAPI {

    @GET("products/categories")
    suspend fun getCategories(): Response<List<ProductCategory>>
}