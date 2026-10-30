package com.pkg.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pkg.data.AddCartProductInfo
import com.pkg.data.GetAllCartProductsInfo
import com.pkg.data.ProductCategory
import com.pkg.data.GetSearchData

class JSONParser {


    fun parseAddCartResponse(json: String) : AddCartProductInfo
    {
        var gson = Gson()
        var addToCartResponse = gson.fromJson<AddCartProductInfo>(json, AddCartProductInfo::class.java)

       val productList = addToCartResponse.productList

        println("Product count: ${productList?.size}")

       /* productList?.forEach { product ->
            println("ID: ${product.productId}")
            println("Title: ${product.title}")
        }*/

        return addToCartResponse
    }
    fun parseGetAllCartsResponse(json: String) : GetAllCartProductsInfo
    {
        var gson = Gson()
       // var getAllCartsResponse = gson.fromJson<GetAllCartProductsInfo>(json, GetAllCartProductsInfo::class.java)
        val response = Gson().fromJson(
            json,
            GetAllCartProductsInfo::class.java
        )
        return response
    }
    fun parseSearchProducts(json: String) : GetSearchData
    {
        var gson = Gson()
        var getSearchResponse = gson.fromJson<GetSearchData>(json, GetSearchData::class.java)

        return getSearchResponse
    }

    fun parseGetCategories(json: String) : List<ProductCategory>
    {
        val categoryList: List<ProductCategory> = Gson().fromJson(
            json,
            object : TypeToken<List<ProductCategory>>() {}.type
        )
        return categoryList
    }

}