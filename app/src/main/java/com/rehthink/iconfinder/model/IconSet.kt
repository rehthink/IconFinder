package com.rehthink.iconfinder.model

import com.google.gson.annotations.SerializedName

data class IconSet(
    @SerializedName("iconset_id")
    val iconSetId: Int,
    @SerializedName("icons_count")
    val iconsCount: Int,
    @SerializedName("is_premium")
    val isPremium: Boolean,
    val name: String,
    val type: String,
    val categories: List<Category>
)