package com.opazoweb.studynomic3.data.taxes

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class TaxTableCollector (current: Context?) {

    private var taxTableMap = mutableMapOf<String, TaxTable>()
    private var context = current?.applicationContext
    private val resources = context?.resources

    init {
        val fileName1 = "taxtable2019"
        csvRead(fileName1)
    }

    fun gettaxTableMap():MutableMap<String, TaxTable> {
        return taxTableMap
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

                if (strArray[4] == "") {

                } else {
                    val year = strArray[0].toInt()
//                val days = strArray[1]
                    val tablename = strArray[2]
                    val payFrom = strArray[3].toInt()
                    val payTo = strArray[4].toInt()
                    val taxInSwedishKr = strArray[5].toInt()


                    if (taxTableMap.containsKey(tablename)) {
                        if (!taxTableMap[tablename]!!.isYearSame(year)) {
                            taxTableMap[tablename] = TaxTable(tablename, year)
                            taxTableMap[tablename]!!.addRange(payFrom, payTo, taxInSwedishKr, year)
                        } else {
                            taxTableMap[tablename]!!.addRange(payFrom, payTo, taxInSwedishKr, year)
                        }
                    } else {

                        taxTableMap[tablename] = TaxTable(tablename, year)
                        taxTableMap[tablename]!!.addRange(payFrom, payTo, taxInSwedishKr, year)
                    }
                }


            }

        }


        ins?.close()
        reader.close()
    }
}