package com.mx.dcc.rickandmortytest.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @SerializedName("count")
    @Expose
    val count: Int,
    @SerializedName("pages")
    @Expose
    val pages: Int,
    @SerializedName("next")
    @Expose
    val next: String,
    @SerializedName("prev")
    val prev: String
)
