package com.pkg.data

data class GetSearchData(

    val products : MutableList<GetSearchProductDetails>,
    val total : Int,
    val skip : Int,
    val limit : Int

)
