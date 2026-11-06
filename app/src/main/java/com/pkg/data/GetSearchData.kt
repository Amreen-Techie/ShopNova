package com.pkg.data
/**
 * Data class representing the search results response containing product listings and pagination info.
 */
data class GetSearchData(

    val products : MutableList<GetSearchProductDetails>,
    val total : Int,
    val skip : Int,
    val limit : Int

)
