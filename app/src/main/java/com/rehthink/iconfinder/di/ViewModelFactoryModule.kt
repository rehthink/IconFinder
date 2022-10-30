package com.rehthink.iconfinder.di


import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import com.rehthink.iconfinder.factory.ViewModelProviderFactory

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory

}