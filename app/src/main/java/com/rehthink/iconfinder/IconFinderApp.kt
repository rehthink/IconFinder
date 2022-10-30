package com.rehthink.iconfinder

import com.rehthink.iconfinder.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class IconFinderApp: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}