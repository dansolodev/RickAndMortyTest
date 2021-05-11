package com.mx.dcc.rickandmortytest.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersViewModel
@Inject
constructor(
    private val repository: CharactersRepository
) : ViewModel() {

    private var currentResult: Flow<PagingData<CharactersModel>>? = null

    fun getCharacters(): Flow<PagingData<CharactersModel>> {
        val lastResult = currentResult
        if (lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<CharactersModel>> = repository.getCharacters()
            .cachedIn(viewModelScope)

        currentResult = newResult
        return newResult
    }

}
