package com.pkg.retrofit

import com.pkg.api.CartAPI
import com.pkg.api.CategoriesAPI
import com.pkg.api.LoginAPI
import com.pkg.api.ProductAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object responsible for configuring and providing Retrofit client instances
 * for network communication across the application.
 */
object AppRetroClient {
    // Base URL for the DummyJSON API used by all network services
    private const val BASE_URL = "https://dummyjson.com/"

    // Standard Retrofit instance (without a converter factory)
    private val retrofit = Retrofit.Builder().baseUrl(BASE_URL).build()

    // Configured Retrofit instance utilizing GsonConverterFactory for JSON deserialization
    private val retrofitCart = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // API service instances created via Retrofit
    val getLoginAPI : LoginAPI = retrofitCart.create(LoginAPI::class.java)
    val getApiCategory : CategoriesAPI = retrofitCart.create(CategoriesAPI::class.java)
    val getApiProduct : ProductAPI = retrofitCart.create(ProductAPI::class.java)
    val getApiCart: CartAPI = retrofitCart.create(CartAPI::class.java)

  //  val getApiSearch: CartAPI = retrofitCart.create(CartAPI::class.java)


}

