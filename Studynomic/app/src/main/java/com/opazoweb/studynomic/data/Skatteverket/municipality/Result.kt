package com.opazoweb.studynomic.data.Skatteverket.municipality


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("begravnings-avgift")
    val begravningsAvgift: String,

    val församling: String,

    @SerializedName("församlings-kod")
    val församlingsKod: String,

    val kommun: String,

    @SerializedName("kommunal-skatt")
    val kommunalSkatt: String,

    val kyrkoavgift: String,
    @SerializedName("landstings-skatt")

    val landstingsSkatt: String,
    @SerializedName("summa, exkl. kyrkoavgift")

    val summaExklKyrkoavgift: String,
    @SerializedName("summa, inkl. kyrkoavgift")

    val summaInklKyrkoavgift: String,
    val år: String
)