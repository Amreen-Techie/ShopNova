package com.pkg.data
/**
 * Data class representing metadata and details associated with a product.
 */
data class DataForProductDetails(
                val slug: String,// URL-friendly unique string identifier for the product
                val name: String,// Name of the product detail item
                val url: String,// URL link for more information about the product
                val tags: List<String>// List of descriptive tags associated with the product

)
