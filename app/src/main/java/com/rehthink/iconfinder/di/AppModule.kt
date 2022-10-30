package com.rehthink.iconfinder.di

import dagger.Module
import dagger.Provides
import com.rehthink.iconfinder.network.ApiService
import com.rehthink.iconfinder.ui.category.CategoryRepository
import com.rehthink.iconfinder.ui.icons.IconRepository
import com.rehthink.iconfinder.ui.iconset.IconSetRepository
import com.rehthink.iconfinder.ui.search.SearchRepository
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideCategoryRepository(apiService: ApiService) = CategoryRepository(apiService)

    @Singleton
    @Provides
    fun provideIconSetRepository(apiService: ApiService) = IconSetRepository(apiService)

    @Singleton
    @Provides
    fun provideIconRepository(apiService: ApiService) = IconRepository(apiService)

    @Singleton
    @Provides
    fun provideSearchRepository(apiService: ApiService) = SearchRepository(apiService)
}