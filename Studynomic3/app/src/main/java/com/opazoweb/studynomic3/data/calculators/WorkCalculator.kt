package com.opazoweb.studynomic3.data.calculators

import android.content.SharedPreferences
import com.opazoweb.studynomic3.data.location.Municipality
import com.opazoweb.studynomic3.data.taxes.TaxTable

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

    fun payAfterTaxes(income: Int, municipality: String, isChurch: Boolean):String {
        val taxTableNr = taxTable(municipality, isChurch)
        val burialFee = municipalityMap[municipality]!!.burialFee
        return taxTableMap[taxTableNr]!!.leftAfterTaxes(income, burialFee).toString()

    }
}