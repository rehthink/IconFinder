package com.rehthink.iconfinder.network.response

import com.google.gson.annotations.SerializedName

open class BaseResponse (

    @SerializedName("total_count")
    var totalCount: Int = 0
)