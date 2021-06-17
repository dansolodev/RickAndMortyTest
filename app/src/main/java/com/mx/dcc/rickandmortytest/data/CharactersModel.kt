package com.mx.dcc.rickandmortytest.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharactersModel(
    val id: Int,
    val image: String,
    val name: String,
    val status: String,
    val species: String,
    val origin: String,
    val urlOrigin: String,
    val location: String,
    val urlLocation: String
) : Parcelable
