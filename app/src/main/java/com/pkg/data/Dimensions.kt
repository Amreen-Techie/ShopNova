package com.pkg.data

import android.annotation.SuppressLint
import android.os.Parcelable

/*@SuppressLint("ParcelCreator")
@Parcelize*/
data class Dimensions(
    val width: Double,
    val height: Double,
    val depth: Double)
    //: Parcelable
