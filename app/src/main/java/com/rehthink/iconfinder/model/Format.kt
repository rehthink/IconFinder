package com.rehthink.iconfinder.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Format(
    @SerializedName("download_url")
    val downloadUrl: String,
    @SerializedName("format")
    val format: String,
    @SerializedName("preview_url")
    val preview_url: String
): Parcelable