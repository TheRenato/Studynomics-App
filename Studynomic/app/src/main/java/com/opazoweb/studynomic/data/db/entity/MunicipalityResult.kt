package com.opazoweb.studynomic.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_MUNICPALITY_ID = 0

@Entity(tableName = "municipality_tax")
data class MunicipalityResult(

    @SerializedName("begravnings-avgift")
    val begravningsAvgift: Double,

    @SerializedName("församling")
    val forsamling: String,

//    Kanske använda detta som ID senare
    @SerializedName("församlings-kod")
    val forsamlingsKod: String,

    val kommun: String,

    @SerializedName("kommunal-skatt")
    val kommunalSkatt: Double,

    @SerializedName("summa, exkl. kyrkoavgift")
    val summaExklKyrkoavgift: Double,

    @SerializedName("summa, inkl. kyrkoavgift")
    val summaInklKyrkoavgift: Double,

    @SerializedName("år")
    val ar: Int
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_MUNICPALITY_ID
}