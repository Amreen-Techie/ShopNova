package com.pkg.api

import com.pkg.data.DataForProductDetails
import com.pkg.data.GetSearchData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductAPI {

    @GET("products/categories")
    suspend fun getCategories(): Response<ResponseBody>

    @GET("products/category/smartphones")
    suspend fun getProductByCategories(): Response<GetSearchData>

   /* @GET("products/search?q=tablet")
    suspend fun searchProduct(): Response<GetSearchData>*/
    @GET("products/search")
    suspend fun searchProduct(
        @Query("q") query: String
    ): Response<GetSearchData>
    @GET("products?limit=10&skip=10")
    suspend fun getAllProduct(): Response<GetSearchData>



}