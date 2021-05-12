package com.mx.dcc.rickandmortytest.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.network.CharactersApi
import com.mx.dcc.rickandmortytest.network.mappers.NetworkMapper
import com.mx.dcc.rickandmortytest.repository.CharactersRepository.Companion.NETWORK_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

private const val RICK_AND_MORTY_PAGE_INDEX = 1

class CharactersPagingSource(
    private val service: CharactersApi,
    private val networkMapper: NetworkMapper
) : PagingSource<Int, CharactersModel>() {

    // The refresh key is used for subsequent refresh calls to PagingSource.load after the initial load
    override fun getRefreshKey(state: PagingState<Int, CharactersModel>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharactersModel> {
        val position = params.key ?: RICK_AND_MORTY_PAGE_INDEX

        return try {
            val response = service.getCharacters(position)
            val charactersResponse = response.results
            // TODO: Falta revisar el cache con room
            val characters: List<CharactersModel> = networkMapper.fromEntityLis(charactersResponse)
            val nextKey = if (characters.isEmpty()) {
                null
            } else {
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = characters,
                prevKey = if (position == RICK_AND_MORTY_PAGE_INDEX) null else position - 1,
                nextKey = nextKey
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }


}