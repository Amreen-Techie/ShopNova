package com.pkg.retrofit

import com.pkg.api.CartAPI
import com.pkg.api.CategoriesAPI
import com.pkg.api.LoginAPI
import com.pkg.api.ProductAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppRetroClient {

    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).build()
    private val retrofitCart = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val getLoginAPI : LoginAPI = retrofitCart.create(LoginAPI::class.java)
    val getApiCategory : CategoriesAPI = retrofitCart.create(CategoriesAPI::class.java)
    val getApiProduct : ProductAPI = retrofitCart.create(ProductAPI::class.java)
    val getApiCart: CartAPI = retrofitCart.create(CartAPI::class.java)


}

