package com.pkg.api

import com.pkg.data.DataForProductDetails
import com.pkg.data.GetSearchData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Retrofit API interface for managing product catalogs, searches, and category filters.
 */

interface ProductAPI {
    /**
     * Retrieves raw category response data as an OkHttp [ResponseBody].
     */

    @GET("products/categories")
    suspend fun getCategories(): Response<ResponseBody>

    /**
     * Fetches products belonging to a specific category path.
     * @param category The dynamic path segment representing the category name.
     */

    @GET("products/category/{category}")
    suspend fun getProductByCategories(
        @Path("category") category: String
    ): Response<GetSearchData>

    /**
     * Searches for products matching a query parameter string.
     * @param searchText The query term passed via the `q` query parameter.
     */

    @GET("products/search")
    suspend fun searchProduct(
        @Query("q") searchText: String
    ): Response<GetSearchData>


    /**
     * Retrieves a complete list of all available products.
     */

    @GET("products?")
    suspend fun getAllProduct(): Response<GetSearchData>



}