package com.mx.dcc.rickandmortytest.di

import androidx.lifecycle.ViewModel
import com.mx.dcc.rickandmortytest.ui.main.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindCharactersViewModel(charactersViewModel: CharactersViewModel): ViewModel
}