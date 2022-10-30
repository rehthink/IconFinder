package com.rehthink.iconfinder.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.rehthink.iconfinder.ui.category.CategoryFragment
import com.rehthink.iconfinder.ui.icons.IconsFragment
import com.rehthink.iconfinder.ui.iconset.IconSetFragment
import com.rehthink.iconfinder.ui.search.SearchFragment

@Module
abstract class MainFragmentBuilderModules {

    @ContributesAndroidInjector
    abstract fun contributeCategoryFragment(): CategoryFragment

    @ContributesAndroidInjector
    abstract fun contributeIconSetFragment(): IconSetFragment

    @ContributesAndroidInjector
    abstract fun contributeIconsFragment(): IconsFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment
}