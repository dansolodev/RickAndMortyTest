package com.mx.dcc.rickandmortytest.repository.details

import com.mx.dcc.rickandmortytest.data.detail.CharacterModel
import com.mx.dcc.rickandmortytest.network.CharactersApi
import com.mx.dcc.rickandmortytest.network.mappers.CharacterMapper
import com.mx.dcc.rickandmortytest.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CharacterRepository
@Inject
constructor(
    private val service: CharactersApi,
    private val mapper: CharacterMapper
) {

    fun getEpisodes(ideCharacter: Int): Flow<DataState<CharacterModel>> = flow {
        emit(DataState.Loading)
        try {
            val response = service.getCharacterEpisodes(ideCharacter)
            // TODO: Check save in Room
            val model = mapper.fromEntity(response)
            emit(DataState.Success(model))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}