package com.mx.dcc.rickandmortytest.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("image")
    @Expose
    val image: String,
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
    val origin: OriginResponse,
    @SerializedName("location")
    @Expose
    val location: LocationResponse
)
