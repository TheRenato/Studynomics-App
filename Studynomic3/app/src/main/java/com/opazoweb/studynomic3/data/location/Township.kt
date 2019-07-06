package com.opazoweb.studynomic3.data.location

class Township (
    val name: String,
    val belongsTo: String,
    var sumInclChurchFee: Double,
    var churchFee: Double,
    var fromYear: Int
) {
    private fun isYearSame (checkYear: Int):Boolean {
        return fromYear == checkYear
    }

    fun updateTownship (newSumInclChurchFee: Double, newChurchFee: Double, newYear: Int) {
        if (isYearSame(newYear)) {

        } else {
            sumInclChurchFee = newSumInclChurchFee
            churchFee = newChurchFee
            fromYear = newYear
        }
    }

    fun doesBelongTo(MunicipalityName: String): Boolean {
        return belongsTo == MunicipalityName
    }


}