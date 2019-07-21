package com.opazoweb.studynomic3.data.csn

class CsnMaxIncome(
    val name: String,
    var fullTime: Int,
    var threeQuarterTime: Int,
    var halfTime: Int
) {
    fun getMaxIncome(pace: Int): Int {
        var result = 0

        when (pace) {
            50 -> result = halfTime
            75 -> result = threeQuarterTime
            100 -> result = fullTime
            else -> {  }
        }

        return result
    }

}