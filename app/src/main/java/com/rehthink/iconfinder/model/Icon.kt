package com.rehthink.iconfinder.model

data class Icon(
    val categories: List<Category>,
    val icon_id: Int,
    val is_icon_glyph: Boolean,
    val is_premium: Boolean,
    val raster_sizes: List<RasterSize>,
    val type: String
)