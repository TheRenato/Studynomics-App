package com.opazoweb.studynomic.data.Skatteverket.municipality


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("begravnings-avgift")
    val begravningsAvgift: Double,

    val församling: String,

    @SerializedName("församlings-kod")
    val församlingsKod: String,

    val kommun: String,

    @SerializedName("kommunal-skatt")
    val kommunalSkatt: Double,

    val kyrkoavgift: Double,

    @SerializedName("landstings-skatt")
    val landstingsSkatt: Double,

    @SerializedName("summa, exkl. kyrkoavgift")
    val summaExklKyrkoavgift: Double,

    @SerializedName("summa, inkl. kyrkoavgift")
    val summaInklKyrkoavgift: Double,

    val år: Int
)