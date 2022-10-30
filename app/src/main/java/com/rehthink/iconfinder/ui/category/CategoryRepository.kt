package com.rehthink.iconfinder.ui.category

import com.rehthink.iconfinder.base.BaseRepository
import com.rehthink.iconfinder.network.ApiService
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val apiService: ApiService): BaseRepository() {

    suspend fun getCategory(count: Int) = apiService.getCategories(count).asyncExecute()
}