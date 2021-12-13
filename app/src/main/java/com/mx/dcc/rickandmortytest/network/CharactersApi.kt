package com.mx.dcc.rickandmortytest.network

import com.mx.dcc.rickandmortytest.data.MainCharactersResponse
import com.mx.dcc.rickandmortytest.data.detail.CharacterResponse
import com.mx.dcc.rickandmortytest.data.detail.EpisodeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharactersApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): MainCharactersResponse

    @GET("character/{id}")
    suspend fun getCharacterEpisodes(
        @Path("id") id: Int
    ): CharacterResponse

    @GET("episode/{id}")
    fun getEpisode(
        @Path("id") id: Int
    ): Call<EpisodeResponse>

}