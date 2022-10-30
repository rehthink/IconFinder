package com.rehthink.iconfinder.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RasterSize(
    val formats: List<Format>,
    val size: Int,
    val size_height: Int,
    val size_width: Int
): Parcelable