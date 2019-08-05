package com.opazoweb.studynomic3.data.calculators


import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import androidx.test.platform.app.InstrumentationRegistry

import com.opazoweb.studynomic3.data.location.Municipality
import com.opazoweb.studynomic3.data.location.MunicipalityCollector
import com.opazoweb.studynomic3.data.taxes.TaxTable
import com.opazoweb.studynomic3.data.taxes.TaxTableCollector

import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test


class WorkCalculatorTest2 {

    lateinit var workCalculator: WorkCalculator
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
    val someContext = InstrumentationRegistry.getInstrumentation().targetContext

        sharedpref = PreferenceManager.getDefaultSharedPreferences(someContext)
        municipalityCollector = MunicipalityCollector(someContext)
        municipalityMap = municipalityCollector.getMunicipalityMap()
        taxTableCollector = TaxTableCollector(someContext)
        taxTableMap = taxTableCollector.gettaxTableMap()
        workCalculator = WorkCalculator(municipalityMap, taxTableMap, sharedpref)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun taxTable() {
        val output = workCalculator.taxTable(municipality, isChurch).toInt()

        val expected = 30

        assertEquals(expected, output)
    }

    @Test
    fun payAfterTaxes() {
        val income = 10000
        val output = workCalculator.payAfterTaxes(income, municipality, isChurch)
        val expected = 8615
        // expected is taken from https://app.skatteverket.se/rakna-skatt-client-skut-skatteutrakning/lon-efter-skattetabell/fyll-i-din-lon

        assertEquals(expected.toDouble(), output.toDouble(), 100.0)
    }

    @Test
    fun totalPay() {
        val income = 10000
        val weeks = 4
        val leave = 50.toDouble()

        val output = workCalculator.totalPay(weeks, income, leave)
        val expected = 55000

        assertEquals(expected, output)

    }

    @Test
    fun getMunicipalityMap() {
        var output = false
        val map = workCalculator.municipalityMap
        output = map is MutableMap<String, Municipality>
        assert(output)
    }

    @Test
    fun getTaxTableMap() {
        var output = false
        val map = workCalculator.taxTableMap
        output = map is MutableMap<String, TaxTable>
        assert(output)
    }

    @Test
    fun getSharedpref() {
        var output = false
        val map = workCalculator.sharedpref
        output = map is SharedPreferences
        assert(output)
    }
}