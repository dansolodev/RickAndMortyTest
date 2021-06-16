package com.mx.dcc.rickandmortytest.di

import androidx.lifecycle.ViewModel
import com.mx.dcc.rickandmortytest.ui.detail.DetailCharacterViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailCharacterViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailCharacterViewModel::class)
    abstract fun bindDetailCharacterViewModel(detailCharacterViewModel: DetailCharacterViewModel): ViewModel

}