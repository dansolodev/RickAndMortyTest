package com.mx.dcc.rickandmortytest.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("species")
    @Expose
    val species: String,
    @SerializedName("origin")
    @Expose
    val origin: OriginResponse
)
