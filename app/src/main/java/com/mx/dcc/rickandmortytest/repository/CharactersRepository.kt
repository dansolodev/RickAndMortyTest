package com.mx.dcc.rickandmortytest.repository

import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.data.CharactersResponse
import com.mx.dcc.rickandmortytest.network.CharactersApi
import com.mx.dcc.rickandmortytest.network.mappers.NetworkMapper
import com.mx.dcc.rickandmortytest.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharactersRepository
@Inject
constructor(
    private val charactersApi: CharactersApi,
    private val networkMapper: NetworkMapper
) {

    suspend fun getCharacters(): Flow<DataState<List<CharactersModel>>> = flow {
        emit(DataState.Loading)
        try {
            val charactersResponse: List<CharactersResponse> = charactersApi.getCharacters().results
            val characters: List<CharactersModel> = networkMapper.fromEntityLis(charactersResponse)
            // TODO("Falta el cache con room")
            emit(DataState.Success(characters))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(DataState.Error(e))
        }
    }

}