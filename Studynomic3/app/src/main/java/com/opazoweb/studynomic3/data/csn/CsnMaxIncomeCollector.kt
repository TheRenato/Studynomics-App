package com.opazoweb.studynomic3.data.csn

import android.content.Context
import com.opazoweb.studynomic3.data.taxes.TaxTable
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class CsnMaxIncomeCollector (current: Context?) {
    private var csnMaxIncomeMap = mutableMapOf<Int, Int>()
    private var context = current?.applicationContext
    private val resources = context?.resources

    init {
        val fileName1 = "csn2019"
        csvRead(fileName1)
    }

    fun getCsnMaxIncomeMap():MutableMap<Int, Int> {
        return csnMaxIncomeMap
    }

    @Throws(IOException::class)
    private fun csvRead(fileName: String) {


        val ins: InputStream? = resources?.openRawResource(
            resources.getIdentifier(
                fileName,
                "raw",
                context?.packageName
            )
        )


        val reader = BufferedReader(
            InputStreamReader(ins, Charset.forName("UTF-8"))
        )

        var rows = reader.readLine()

        while ( rows != null ) {
            rows = reader.readLine()

            if (rows != null) {
                val strArray = rows.split(";")


                val weeks = strArray[0].toInt()
                val maxIncome = strArray[1].toInt()


                if (csnMaxIncomeMap.containsKey(weeks)) {
                    if (maxIncome == csnMaxIncomeMap[weeks]) {}
                    else { csnMaxIncomeMap[weeks] = maxIncome }
                }
                else { csnMaxIncomeMap[weeks] = maxIncome }

            }
        }


        ins?.close()
        reader.close()
    }
}