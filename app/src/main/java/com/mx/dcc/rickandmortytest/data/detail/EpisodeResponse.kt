package com.mx.dcc.rickandmortytest.data.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("id")
    @Expose
    val id: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("air_date")
    @Expose
    val air_date: String,
    @SerializedName("episode")
    @Expose
    val episode: String,
    @SerializedName("created")
    @Expose
    val created: String
)
