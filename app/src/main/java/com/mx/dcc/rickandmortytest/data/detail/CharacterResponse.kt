package com.mx.dcc.rickandmortytest.data.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("episode")
    @Expose
    val episode: List<String>
)
