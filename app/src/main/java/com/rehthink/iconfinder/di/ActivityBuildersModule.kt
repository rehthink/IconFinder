package com.rehthink.iconfinder.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.rehthink.iconfinder.ui.FullScreenViewActivity
import com.rehthink.iconfinder.ui.MainActivity

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeFullScreenViewActivity(): FullScreenViewActivity
}