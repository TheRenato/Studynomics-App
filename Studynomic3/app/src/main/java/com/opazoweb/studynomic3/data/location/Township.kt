package com.opazoweb.studynomic3.data.location

class Township (
    val name: String,
    val belongsTo: String,
    var sumInclChurchFee: Double,
    var churchFee: Double,
    var fromYear: Int
) {
    fun isYearSame (checkYear: Int):Boolean {
        return fromYear == checkYear
    }


    fun doesBelongTo(MunicipalityName: String): Boolean {
        return belongsTo == MunicipalityName
    }


}