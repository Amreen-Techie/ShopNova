package com.pkg.data
/**
 * Data class representing product metadata such as timestamps and identification codes.
 */
data class Meta(val createdAt: String,
                val updatedAt: String,
                val barcode: String,
                val qrCode: String)
