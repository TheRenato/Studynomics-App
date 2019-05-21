package com.opazoweb.studynomic.data


data class MunicipalitySkatteverketResponse(
    val limit: Int,
    val next: String,
    val offset: Int,
    val resultCount: Int,
    val results: List<Result>
)