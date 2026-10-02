package com.pkg

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pkg.data.AddCartProductInfo
import com.pkg.data.GetAllCartProductsInfo
import com.pkg.data.GetCategoriesData
import com.pkg.data.GetSearchData

class JSONParser {


    fun parseAddCartResponse(json: String) : AddCartProductInfo
    {
        var gson = Gson()
        var addToCartResponse = gson.fromJson<AddCartProductInfo>(json, AddCartProductInfo::class.java)

     /*   val productList = addToCartResponse.products

        println("Product count: ${productList?.size}")

        productList?.forEach { product ->
            println("ID: ${product.productId}")
            println("Title: ${product.title}")
        }*/

        return addToCartResponse
    }
    fun parseGetAllCartsResponse(json: String) : GetAllCartProductsInfo
    {
        var gson = Gson()
        var getAllCartsResponse = gson.fromJson<GetAllCartProductsInfo>(json, GetAllCartProductsInfo::class.java)

        return getAllCartsResponse
    }
    fun parseSearchProducts(json: String) : GetSearchData
    {
        var gson = Gson()
        var getSearchResponse = gson.fromJson<GetSearchData>(json, GetSearchData::class.java)

        return getSearchResponse
    }
// var gson = Gson()
    // var getCategoriesData = gson.fromJson<GetCategoriesData>(json, GetCategoriesData::class.java)

    fun parseGetCategories(json: String) : List<GetCategoriesData>
    {
        val categoryList: List<GetCategoriesData> = Gson().fromJson(
            json,
            object : TypeToken<List<GetCategoriesData>>() {}.type
        )
        return categoryList
    }
   /* fun parseSearchProducts(json: String) : GetAllCartProductsInfo
    {
        var gson = Gson()
        var getAllCartsResponse = gson.fromJson<GetAllCartProductsInfo>(json, GetAllCartProductsInfo::class.java)

        return getAllCartsResponse
    }*/
}
