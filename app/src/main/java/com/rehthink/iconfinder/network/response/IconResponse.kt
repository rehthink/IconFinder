package com.rehthink.iconfinder.network.response

import com.rehthink.iconfinder.model.Icon

data class IconResponse(
    val icons: List<Icon>
) : BaseResponse()