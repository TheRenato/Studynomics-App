package com.opazoweb.studynomic3.data.csn

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class CsnMaxIncomeCollector (current: Context?) {
    private var csnMaxIncomeMap = mutableMapOf<Int, CsnMaxIncome>()
    private var context = current?.applicationContext
    private val resources = context?.resources

    init {
        val fileName1 = "csn2019"
        csvRead(fileName1)
    }

    fun getCsnMaxIncomeMap():MutableMap<Int, CsnMaxIncome> {
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
                val maxIncome100 = strArray[1].toInt()
                val maxIncome75 = strArray[2].toInt()
                val maxIncome50 = strArray[3].toInt()

                val csnMaxIncome = CsnMaxIncome(
                    "$weeks Weeks",
                    maxIncome100,
                    maxIncome75,
                    maxIncome50)

                if (csnMaxIncomeMap.containsKey(weeks)) {
                    if (csnMaxIncome == csnMaxIncomeMap[weeks] ) {}
                    else {
                        csnMaxIncomeMap[weeks] = csnMaxIncome }
                }
                else {
                    csnMaxIncomeMap[weeks] = csnMaxIncome }

            }
        }


        ins?.close()
        reader.close()
    }

}