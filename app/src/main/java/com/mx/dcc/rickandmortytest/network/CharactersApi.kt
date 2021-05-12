package com.mx.dcc.rickandmortytest.network

import com.mx.dcc.rickandmortytest.data.MainCharactersResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): MainCharactersResponse
}