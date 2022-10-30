package com.rehthink.iconfinder.ui.icons

import com.rehthink.iconfinder.base.BaseRepository
import com.rehthink.iconfinder.network.ApiService
import javax.inject.Inject

class IconRepository @Inject constructor(private val apiService: ApiService) : BaseRepository() {

    suspend fun loadIcons(iconSetId: Int, count: Int) =
        apiService.getIconsList(iconSetId, count).asyncExecute()
}