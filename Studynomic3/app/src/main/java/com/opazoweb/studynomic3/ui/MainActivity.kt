package com.opazoweb.studynomic3.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.opazoweb.studynomic3.R
import com.opazoweb.studynomic3.data.location.Municipality
import com.opazoweb.studynomic3.data.location.Township
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private var municipalityMap = mutableMapOf<String, Municipality>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)


        bottom_nav.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController)

        csvRead("taxes2019")
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp( navController, null)
    }

    @Throws(IOException::class)
    private fun csvRead(fileName: String) {


        val ins: InputStream = resources.openRawResource(
            resources.getIdentifier(
                fileName,
                "raw",
                packageName
            )
        )

        var reader = BufferedReader(
            InputStreamReader(ins, Charset.forName("UTF-8"))
        )

        var rows = reader.readLine()
        var i = 0
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

    fun getMunicipalityMap():MutableMap<String, Municipality> {
        return municipalityMap
    }
}
