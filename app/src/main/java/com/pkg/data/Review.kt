package com.pkg.data
/**
 * Data class representing a customer review for a product.
 */
data class Review (
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)
