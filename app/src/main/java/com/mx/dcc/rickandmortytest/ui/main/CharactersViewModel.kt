package com.mx.dcc.rickandmortytest.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.repository.CharactersRepository
import com.mx.dcc.rickandmortytest.utils.DataState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class CharactersViewModel
@Inject
constructor(
    private val repository: CharactersRepository
) : ViewModel(){

    private val _dataState: MutableLiveData<DataState<List<CharactersModel>>> = MutableLiveData()
    val dataState: LiveData<DataState<List<CharactersModel>>>
        get() = _dataState

    fun setStateEvent(stateEvent: CharactersStateEvent) {
        viewModelScope.launch {
            when (stateEvent) {
                is CharactersStateEvent.GetCharactersEvent -> {
                    repository.getCharacters().collect { dataState ->
                        _dataState.value = dataState
                    }
                }
            }
        }
    }

}

sealed class CharactersStateEvent {
    object  GetCharactersEvent: CharactersStateEvent()
}