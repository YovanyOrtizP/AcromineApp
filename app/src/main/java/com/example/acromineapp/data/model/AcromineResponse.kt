package com.example.acromineapp.data.model


import com.google.gson.annotations.SerializedName

data class AcromineResponse(
    @SerializedName("lfs")
    val lfs: List<Lf>? = listOf(),
    @SerializedName("sf")
    val sf: String? = ""
)