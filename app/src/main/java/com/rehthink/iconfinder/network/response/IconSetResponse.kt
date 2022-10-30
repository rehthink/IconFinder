package com.rehthink.iconfinder.network.response

import com.google.gson.annotations.SerializedName
import com.rehthink.iconfinder.model.IconSet

data class IconSetResponse(
    @SerializedName("iconsets")
    val iconSets: List<IconSet>
): BaseResponse()