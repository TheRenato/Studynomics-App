package com.opazoweb.studynomic.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_MUNICPALITY_ID = 0

@Entity(tableName = "municipality_tax")
data class MunicipalityResult(
    @SerializedName("begravnings-avgift")
    val burialFee: Double,

    @SerializedName("församling")
    val township: String,

//    Kanske använda detta som ID senare
    @SerializedName("församlings-kod")
    val townshipCode: String,

    @SerializedName("kommun")
    val municipality: String,

    @SerializedName("summa, exkl. kyrkoavgift")
    val sumExclChurchFee: Double,

    @SerializedName("summa, inkl. kyrkoavgift")
    val sumInclChurchFee: Double,

    @SerializedName("år")
    val year: Int
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_MUNICPALITY_ID
}