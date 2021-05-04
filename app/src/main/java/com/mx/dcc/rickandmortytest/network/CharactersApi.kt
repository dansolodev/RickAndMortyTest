package com.mx.dcc.rickandmortytest.network

import com.mx.dcc.rickandmortytest.data.CharactersResponse
import retrofit2.http.GET

interface CharactersApi {
    @GET("character")
    suspend fun getCharacters(): List<CharactersResponse>
}