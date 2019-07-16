package com.opazoweb.studynomic3.data.location

import com.opazoweb.studynomic3.data.taxes.TaxTable
import kotlin.math.roundToInt

class Municipality (
    val name: String,
    var sumExclChurchFee: Double,
    var burialFee: Double,
    var fromYear: Int
    ) {
        var townshipMap: MutableMap<String, Township> = mutableMapOf()

        fun isYearSame (checkYear: Int):Boolean {
            return fromYear == checkYear
        }

        fun addTownship (
            name: String,
            belongsTo: String,
            sumInclChurchFee: Double,
            churchFee: Double,
            fromYear: Int
        ) {
            if (townshipMap.containsKey(name)) {
                if (!townshipMap[name]!!.isYearSame(fromYear)) {
                    townshipMap[name] = Township(name, belongsTo, sumInclChurchFee, churchFee, fromYear)
                } else {

                }
            } else {
                townshipMap[name] = Township(name, belongsTo, sumInclChurchFee, churchFee, fromYear)
            }
        }

        fun municipalityTaxTable(): Int {
            return (sumExclChurchFee + burialFee).roundToInt()
        }

        fun townshipTaxTable(townshipName: String): Int {

            return ((townshipMap[townshipName]!!.sumInclChurchFee) + burialFee).roundToInt()
        }

        fun townshipArray(): Array<CharSequence> {
            val array: ArrayList<String> = ArrayList(townshipMap.keys)
            val cs: Array<CharSequence> = array.toArray(arrayOfNulls<CharSequence>(array.size))

            return cs
        }

        fun isTownship (townshipName: String): Boolean{
            return townshipMap.containsKey(townshipName)
        }
}