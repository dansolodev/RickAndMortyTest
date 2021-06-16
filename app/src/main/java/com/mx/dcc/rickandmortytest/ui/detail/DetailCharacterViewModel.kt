package com.mx.dcc.rickandmortytest.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mx.dcc.rickandmortytest.data.CharactersModel
import com.mx.dcc.rickandmortytest.utils.ApplicationUtils
import javax.inject.Inject

class DetailCharacterViewModel
@Inject
constructor(
    private val applicationUtils: ApplicationUtils
) : ViewModel() {

    private val _characterModel: MutableLiveData<CharactersModel> = MutableLiveData()
    val characterModel: LiveData<CharactersModel>
        get() = _characterModel

    fun setCharacterModel(charactersModel: CharactersModel) {
        _characterModel.value = charactersModel
    }

}