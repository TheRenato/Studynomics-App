package com.opazoweb.studynomic.data.network.response

import com.opazoweb.studynomic.data.db.entity.MunicipalityResult


data class MunicipalitySkatteverketResponse(
    val limit: Int,
    val next: String,
    val offset: Int,
    val resultCount: Int,
    val results: MunicipalityResult
)