package com.mx.dcc.rickandmortytest.di

import androidx.lifecycle.ViewModelProvider
import com.mx.dcc.rickandmortytest.viewmodels.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(
        modelProviderFactory: ViewModelProviderFactory
    ): ViewModelProvider.Factory
}