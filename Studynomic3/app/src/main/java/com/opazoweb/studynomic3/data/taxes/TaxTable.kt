package com.opazoweb.studynomic3.data.taxes

import android.util.Log
import kotlin.math.roundToInt

class TaxTable(
    val name: String,
    var fromYear: Int
) {
    var payRangeMap: MutableMap<String, TaxTableRow> = mutableMapOf()

    fun isYearSame (checkYear: Int):Boolean {
        return fromYear == checkYear
    }

    fun addRange (
        payFrom: Int,
        payTo: Int,
        taxInSwedishKr: Int,
        fromYear: Int
    ) {
        val range = "$payFrom - $payTo"
        if (payRangeMap.containsKey(range)) {
            if (!isYearSame(fromYear)) {
                payRangeMap[range] = TaxTableRow(range, payFrom, payTo, taxInSwedishKr)
            } else {

            }
        } else {
            payRangeMap[range] = TaxTableRow(range, payFrom, payTo, taxInSwedishKr)
        }
    }

    fun getTax (income: Int):Int {
        var tax: Int = 0
        payRangeMap.forEach {
            if (it.value.doesItFit(income)) tax = it.value.taxInSwedishKr
        }
        return tax
    }

    fun leftAfterTaxes (income: Int, burialFee: Double): Int {
        val burialFeeInPro = burialFee/100
        val burialFeeInt = (income * burialFeeInPro).roundToInt()
        val tableTax = getTax(income)
        val left = income - burialFeeInt - tableTax

        return left
    }
}
