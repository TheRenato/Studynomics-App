package com.opazoweb.studynomic3.data.location

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class MunicipalityCollector(current: Context?) {

    private var municipalityMap = mutableMapOf<String, Municipality>()
    private var context: Context = current!!.applicationContext
    private val resources = context.resources

    init {
        val fileName1 = "taxes2019"
        csvRead(fileName1)
    }

    fun getMunicipalityMap():MutableMap<String, Municipality> {
        return municipalityMap
    }

    @Throws(IOException::class)
    private fun csvRead(fileName: String) {


        val ins: InputStream = resources.openRawResource(
            resources.getIdentifier(
                fileName,
                "raw",
                "NULL"
            )
        )

        var reader = BufferedReader(
            InputStreamReader(ins, Charset.forName("UTF-8"))
        )

        var rows = reader.readLine()

        while ( rows != null ) {
            rows = reader.readLine()

            if (rows != null) {
                val strArray = rows.split(";")

                val year = strArray[0].toInt()
                val name = strArray[1]
                val township = strArray[2]
                val sumInclChurchFee = strArray[3].toDouble()
                val sumExclChurchFee = strArray[4].toDouble()
                val burialFee = strArray[5].toDouble()
                val churchFee = strArray[6].toDouble()

                municipalityMap.put(name, Municipality(name, sumExclChurchFee, burialFee, year))
                municipalityMap[name]!!.townshipMap.put(
                    township,
                    Township(township, name, sumInclChurchFee, churchFee, year)
                )
            }

        }


        ins.close()
        reader.close()
    }
}