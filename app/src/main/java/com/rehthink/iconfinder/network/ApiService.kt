package com.rehthink.iconfinder.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.rehthink.iconfinder.network.response.CategoryResponse
import com.rehthink.iconfinder.network.response.IconResponse
import com.rehthink.iconfinder.network.response.IconSetResponse

/****
 *  CREATED BY: Syed Hussain Mehdi at 11 Dec 2020
 */

interface ApiService {

    @GET("categories")
    fun getCategories(@Query("count") count: Int): Call<CategoryResponse>

    @GET("categories/{category}/iconsets")
    fun getIconSetOfCategories(
        @Path("category") category: String,
        @Query("count") count: Int,
        @Query("premium") premium: Boolean
    ): Call<IconSetResponse>

    @GET("iconsets/{iconset_id}/icons")
    fun getIconsList(
        @Path("iconset_id") iconSetId: Int,
        @Query("count") count: Int
    ): Call<IconResponse>

    @GET("icons/search")
    fun search(
        @Query("query") query: String,
        @Query("count") count: Int,
        @Query("premium") premium: Boolean
    ): Call<IconResponse>
}