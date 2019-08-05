package com.opazoweb.studynomic3.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.opazoweb.studynomic3.R
import com.opazoweb.studynomic3.data.csn.CsnMaxIncome
import com.opazoweb.studynomic3.data.csn.CsnMaxIncomeCollector
import com.opazoweb.studynomic3.data.location.Municipality
import com.opazoweb.studynomic3.data.location.MunicipalityCollector
import com.opazoweb.studynomic3.data.taxes.TaxTable
import com.opazoweb.studynomic3.data.taxes.TaxTableCollector
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var context: Context
    private lateinit var municipalityMap:MutableMap<String, Municipality>
    private lateinit var taxTableMap:MutableMap<String, TaxTable>
    private lateinit var csnMaxIncomeMap:MutableMap<Int, CsnMaxIncome>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = applicationContext

        setSupportActionBar(toolbar)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)


        bottom_nav.setupWithNavController(navController)

        NavigationUI.setupActionBarWithNavController(this, navController)

        theCollectors()


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp( navController, null)
    }

    private fun theCollectors() {
        val municipalityCollector = MunicipalityCollector(context)
        municipalityMap = municipalityCollector.getMunicipalityMap()

        val taxTableCollector = TaxTableCollector(context)
        taxTableMap = taxTableCollector.gettaxTableMap()

        val csnMaxIncomeCollector = CsnMaxIncomeCollector(context)
        csnMaxIncomeMap = csnMaxIncomeCollector.getCsnMaxIncomeMap()
    }

    fun getMunicipalityMap() :MutableMap<String, Municipality> {
        return municipalityMap
    }

    fun getTaxTableMap() :MutableMap<String, TaxTable> {
        return taxTableMap
    }

    fun getCsnMaxIncomeMap():MutableMap<Int, CsnMaxIncome> {
        return csnMaxIncomeMap
    }
}
