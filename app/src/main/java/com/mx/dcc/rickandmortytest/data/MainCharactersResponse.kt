package com.mx.dcc.rickandmortytest.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainCharactersResponse(
    @SerializedName("info")
    @Expose
    val info: InfoResponse,
    @SerializedName("results")
    @Expose
    val results: List<CharactersResponse>

)