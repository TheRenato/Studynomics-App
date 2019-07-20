package com.opazoweb.studynomic3.data.calculators

import android.content.SharedPreferences
import com.opazoweb.studynomic3.data.location.Municipality
import com.opazoweb.studynomic3.data.taxes.TaxTable
import kotlin.math.roundToInt

class WorkCalculator(
    val municipalityMap:MutableMap<String, Municipality>,
    val taxTableMap:MutableMap<String, TaxTable>,
    val sharedpref: SharedPreferences
) {
    fun taxTable(municipality: String, isChurch: Boolean):String {
        val tTable: String
        if (isChurch) {
            val townshipName:String? = sharedpref.getString("YOUR_TOWNSHIP", "NULL")
            if (municipalityMap[municipality]!!.isTownship(townshipName!!)) {
                tTable = municipalityMap[municipality]?.townshipTaxTable(townshipName).toString()
            } else {
                tTable = "Please Select a Parish"
            }
        } else {
            tTable = municipalityMap[municipality]!!.municipalityTaxTable().toString()
        }
        return tTable
    }

    fun payAfterTaxes(income: Int, municipality: String, isChurch: Boolean, leave: Int = 0):String {
        val taxTableNr = taxTable(municipality, isChurch)
        val burialFee = municipalityMap[municipality]!!.burialFee

        val incomeAfterLeave = income * leaveToDeci(leave)
        val afterLeave = taxTableMap[taxTableNr]!!.leftAfterTaxes(incomeAfterLeave.toInt(), burialFee)
        return afterLeave.toString()

    }

    fun totalPay(studieWeeks: Int, income: Int, leave: Int = 0): Int {
        val workWeeks = 26 - studieWeeks

        val workMonths = (workWeeks/4.25).roundToInt()
        val studieMonths = (studieWeeks/4.25).roundToInt()
        val workPay = income * workMonths
        val studiePay = (income * leaveToDeci(leave)) * studieMonths

        val sum = workPay + studiePay.roundToInt()

        return sum
    }

    private fun leaveToDeci(leave: Int):Double {
        return (100 - leave.toDouble()) / 100
    }
}