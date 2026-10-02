package com.pkg.data

data class GetSearchProductDetails(

    val id: String,
    val title: String,
    val description: String,
    val category: String,
    val price: String,
    val discountPercentage: String,
    val rating: String,
    val stock: String,
    val tags: List<String>,

    //tagsValue
    val brand: String,
    val sku: String,
    val weight: String,
    val dimensions: Dimensions,
    //dimensionList
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val reviews: List<Review>,

    //reviewsList
    val returnPolicy: String,
    val minimumOrderQuantity: String,
    val meta: Meta,
   // metaList
    val images: List<String>,

    // images
    val thumbnail: String,
)
