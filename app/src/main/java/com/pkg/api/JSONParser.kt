package com.pkg.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pkg.data.AddCartProductInfo
import com.pkg.data.GetAllCartProductsInfo
import com.pkg.data.ProductCategory
import com.pkg.data.GetSearchData

/**
 * Utility class responsible for parsing raw JSON strings into specific data models using Gson.
 */

class JSONParser {

    /**
     * Parses the JSON response for adding an item to the cart.
     * @param json Raw JSON response string.
     * @return Mapped [AddCartProductInfo] object.
     */
    fun parseAddCartResponse(json: String) : AddCartProductInfo
    {
        var gson = Gson()
        var addToCartResponse = gson.fromJson<AddCartProductInfo>(json, AddCartProductInfo::class.java)

       val productList = addToCartResponse.productList

        println("Product count: ${productList?.size}")

        return addToCartResponse
    }

    /**
     * Parses the JSON response for fetching all items currently in carts.
     * @param json Raw JSON response string.
     * @return Mapped [GetAllCartProductsInfo] object.
     */

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
    /**
     * Parses the JSON response for product search queries.
     * @param json Raw JSON response string.
     * @return Mapped [GetSearchData] object containing search results.
     */
    fun parseSearchProducts(json: String) : GetSearchData
    {
        var gson = Gson()
        var getSearchResponse = gson.fromJson<GetSearchData>(json, GetSearchData::class.java)

        return getSearchResponse
    }
    /**
     * Parses the JSON response for retrieving a list of product categories.
     * Uses TypeToken to correctly handle generic List types with Gson.
     * @param json Raw JSON response string.
     * @return List of [ProductCategory] items.
     */

    fun parseGetCategories(json: String) : List<ProductCategory>
    {
        val categoryList: List<ProductCategory> = Gson().fromJson(
            json,
            object : TypeToken<List<ProductCategory>>() {}.type
        )
        return categoryList
    }

}