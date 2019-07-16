package com.opazoweb.studynomic3.ui.summery

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.preference.PreferenceManager
import com.opazoweb.studynomic3.R
import com.opazoweb.studynomic3.data.location.Municipality
import com.opazoweb.studynomic3.data.taxes.TaxTable
import com.opazoweb.studynomic3.ui.MainActivity
import kotlinx.android.synthetic.main.summery_fragment.*

class SummeryFragment : Fragment() {

    companion object {
        fun newInstance() = SummeryFragment()
    }

    private lateinit var mainActivity: MainActivity
    private lateinit var municipalityMap:MutableMap<String, Municipality>
    private lateinit var taxTableMap:MutableMap<String, TaxTable>
    private lateinit var sharedpref: SharedPreferences
    private var isChurch:Boolean = false

    private lateinit var viewModel: SummeryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.summery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SummeryViewModel::class.java)


        mainActivity = (activity as MainActivity)
        municipalityMap = mainActivity.getMunicipalityMap()
        taxTableMap = mainActivity.getTaxTableMap()
        sharedpref = PreferenceManager.getDefaultSharedPreferences(context)
        isChurch = sharedpref.getBoolean("CHURCH_FEE", false)

        summery()
    }

    private fun summery() {


        val dateFromTO:String = sharedpref.getString("START_DATE", "20190902")!! + " - " +
                sharedpref.getString("END_DATE", "20191231")
        val municipality:String = sharedpref.getString("YOUR_RESIDENT", "STOCKHOLM")!!.toString()
        val income = sharedpref.getString("FULLTIME_PAY", "2000")!!.toInt()

        textviewSummeryMunicipality.text = municipality
        textviewSummeryStudyDate.text = dateFromTO
        textviewSummeryTaxTable.text = taxTable(municipality)
        textviewSummeryYourPay.text = payAfterTaxes(income, municipality)
    }

    private fun taxTable(municipality: String):String {
        val tTable: String
        if (isChurch) {
            val townshipName:String? = sharedpref.getString("YOUR_TOWNSHIP", "NULL")
            if (municipalityMap[municipality]!!.isTownship(townshipName!!)) {
                tTable = municipalityMap[municipality]?.townshipTaxTable(townshipName).toString()
            } else {
                tTable = "Please Select a Parish"
            }
        } else {
            tTable = municipalityMap[municipality]!!.municipalityTaxTable().toString()
        }
        return tTable
    }


    private fun payAfterTaxes(income: Int, municipality: String):String {
        val taxTableNr = taxTable(municipality)
        val burialFee = municipalityMap[municipality]!!.burialFee
        return taxTableMap[taxTableNr]!!.leftAfterTaxes(income, burialFee).toString()
    }
}
