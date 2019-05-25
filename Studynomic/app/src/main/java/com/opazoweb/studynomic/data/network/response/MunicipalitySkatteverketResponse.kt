package com.opazoweb.studynomic.data.network.response

import com.google.gson.annotations.SerializedName
import com.opazoweb.studynomic.data.db.entity.MunicipalityResult


data class MunicipalitySkatteverketResponse(
    val limit: Int,
    val next: String,
    val offset: Int,
    val resultCount: Int,
    @SerializedName("results")
    val municipalityResult: List<MunicipalityResult>
)