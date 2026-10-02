package com.pkg.api

import com.pkg.data.GetCategoriesData
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface CategoriesAPI {

  /*  @GET("products/categories")
    suspend fun getCategories(): Response<GetCategoriesData>*/
    @GET("products/categories")
    suspend fun getCategories(): Response<List<GetCategoriesData>>
}