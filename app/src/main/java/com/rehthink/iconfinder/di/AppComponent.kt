package com.rehthink.iconfinder.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import com.rehthink.iconfinder.IconFinderApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ViewModelFactoryModule::class,
        AndroidInjectionModule::class,
        ActivityBuildersModule::class,
        MainFragmentBuilderModules::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<IconFinderApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}