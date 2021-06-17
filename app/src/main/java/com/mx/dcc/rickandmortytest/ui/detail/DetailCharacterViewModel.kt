package com.mx.dcc.rickandmortytest.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.data.detail.CharacterModel
import com.mx.dcc.rickandmortytest.repository.details.CharacterRepository
import com.mx.dcc.rickandmortytest.utils.DataState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailCharacterViewModel
@Inject
constructor(
    private val repository: CharacterRepository
) : ViewModel() {

    private val _characterModel: MutableLiveData<CharactersModel> = MutableLiveData()
    val characterModel: LiveData<CharactersModel>
        get() = _characterModel

    private val _characterEpisodesModel: MutableLiveData<DataState<CharacterModel>> = MutableLiveData()
    val characterEpisodesModel: LiveData<DataState<CharacterModel>>
        get() = _characterEpisodesModel

    fun setCharacterModel(charactersModel: CharactersModel) {
        getEpisodes(charactersModel.id)
        _characterModel.value = charactersModel
    }

    private fun getEpisodes(ideCharacter: Int) {
        viewModelScope.launch {
            repository.getEpisodes(ideCharacter).collect {
                _characterEpisodesModel.value = it
            }
        }
    }

}