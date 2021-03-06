package com.mx.dcc.rickandmortytest.di

import com.mx.dcc.rickandmortytest.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [MainViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity
}