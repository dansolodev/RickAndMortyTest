package com.mx.dcc.rickandmortytest.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Object to expose info for
 * @see CharactersResponse
 */
data class LocationResponse(
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("url")
    @Expose
    val url: String
)