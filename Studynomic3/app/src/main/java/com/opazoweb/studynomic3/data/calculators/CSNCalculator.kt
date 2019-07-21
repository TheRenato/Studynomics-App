package com.opazoweb.studynomic3.data.calculators

import android.annotation.SuppressLint
import com.opazoweb.studynomic3.data.csn.CsnMaxIncome
import java.text.SimpleDateFormat
import kotlin.math.roundToInt


class CSNCalculator(val csnMaxIncomeMap: MutableMap<Int, CsnMaxIncome>) {

    @SuppressLint("SimpleDateFormat")
    fun studieInWeeks(startDate: String, endDate: String): Int {

        val startDateInTime = SimpleDateFormat("yyyyMMdd").parse(startDate)
        val endDateInTime = SimpleDateFormat("yyyyMMdd").parse(endDate)

        val totTime = endDateInTime.time - startDateInTime.time

        val totSek = totTime.toDouble()/1000
        val totMin = totSek/60
        val totHtime = totMin/60
        val totDay = totHtime/24
        val totWeek = totDay/7

        return totWeek.roundToInt()
    }

    fun maxIncome(weeks: Int, pace: Int = 100): Int {
        return csnMaxIncomeMap[weeks]!!.getMaxIncome(pace)
    }

}