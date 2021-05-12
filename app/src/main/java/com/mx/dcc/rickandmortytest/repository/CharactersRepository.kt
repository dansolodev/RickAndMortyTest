package com.mx.dcc.rickandmortytest.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.network.CharactersApi
import com.mx.dcc.rickandmortytest.network.mappers.NetworkMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepository
@Inject
constructor(
    private val charactersApi: CharactersApi,
    private val networkMapper: NetworkMapper
) {

    fun getCharacters(): Flow<PagingData<CharactersModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {CharactersPagingSource(service = charactersApi, networkMapper)}
        ).flow
    }

    companion object {
        internal const val NETWORK_PAGE_SIZE = 50
    }

}