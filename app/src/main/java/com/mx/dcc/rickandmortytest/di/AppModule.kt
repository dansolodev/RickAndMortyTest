package com.mx.dcc.rickandmortytest.di

import com.mx.dcc.rickandmortytest.BuildConfig
import com.mx.dcc.rickandmortytest.network.CharactersApi
import com.mx.dcc.rickandmortytest.network.mappers.NetworkMapper
import com.mx.dcc.rickandmortytest.utils.ApplicationUtils
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

    @Singleton
    @Provides
    fun provideCharactersApi(retrofit: Retrofit.Builder): CharactersApi {
        return retrofit.build().create(CharactersApi::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkMapper(): NetworkMapper {
        return NetworkMapper()
    }

    @Singleton
    @Provides
    fun provideApplicationsUtils(): ApplicationUtils {
        return ApplicationUtils()
    }

}