package com.pkg.data

import android.annotation.SuppressLint
import android.os.Parcelable
/**
 * Data class representing physical dimensions of a product.
 */
data class Dimensions(
    val width: Double,// Width measurement
    val height: Double,// Height measurement
    val depth: Double)// Depth measurement
