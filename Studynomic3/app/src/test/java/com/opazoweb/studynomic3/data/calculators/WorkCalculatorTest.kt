package com.opazoweb.studynomic3.data.calculators

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.test.platform.app.InstrumentationRegistry.*
import com.opazoweb.studynomic3.data.location.Municipality
import com.opazoweb.studynomic3.data.location.MunicipalityCollector
import com.opazoweb.studynomic3.data.taxes.TaxTable
import com.opazoweb.studynomic3.data.taxes.TaxTableCollector

import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WorkCalculatorTest {

    lateinit var context: Context
    lateinit var sharedpref: SharedPreferences
    lateinit var municipalityMap: MutableMap<String, Municipality>
    lateinit var taxTableMap: MutableMap<String, TaxTable>
    lateinit var municipalityCollector: MunicipalityCollector
    lateinit var taxTableCollector: TaxTableCollector

    var municipality = "STOCKHOLM"
    var isChurch = false

    @Before
    fun setUp() {
        context = getInstrumentation().context
        sharedpref = PreferenceManager.getDefaultSharedPreferences(context)
        municipalityCollector = MunicipalityCollector(context)
        municipalityMap = municipalityCollector.getMunicipalityMap()
        taxTableMap = taxTableCollector.gettaxTableMap()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun taxTable() {
        val output = WorkCalculator(municipalityMap, taxTableMap, sharedpref)
            .taxTable(municipality, isChurch)

        val expected = 30

        assertEquals(expected, output)
    }

    @Test
    fun payAfterTaxes() {
    }

    @Test
    fun totalPay() {
    }

    @Test
    fun getMunicipalityMap() {
    }

    @Test
    fun getTaxTableMap() {
    }

    @Test
    fun getSharedpref() {
    }
}