package com.rehthink.iconfinder.network.response

import com.rehthink.iconfinder.model.Category

data class CategoryResponse(
    val categories: List<Category>
) : BaseResponse()