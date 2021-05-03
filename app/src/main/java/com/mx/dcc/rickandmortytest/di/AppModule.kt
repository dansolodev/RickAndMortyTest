package com.mx.dcc.rickandmortytest.di

import com.mx.dcc.rickandmortytest.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        // Creamos el interceptor y le indicamos el log level a usar
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        // Asociamos el interceptor a las peticiones
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        return  httpClient.build()
    }

    @Singleton
    @Provides
    fun provideRetrofitInstance(client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
    }

}