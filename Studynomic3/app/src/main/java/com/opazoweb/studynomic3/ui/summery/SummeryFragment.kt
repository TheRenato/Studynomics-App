package com.opazoweb.studynomic3.ui.summery

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager

import com.opazoweb.studynomic3.R
import com.opazoweb.studynomic3.data.location.Municipality
import com.opazoweb.studynomic3.data.location.MunicipalityCollector
import com.opazoweb.studynomic3.ui.MainActivity
import kotlinx.android.synthetic.main.summery_fragment.*
import kotlin.coroutines.coroutineContext

class SummeryFragment : Fragment() {

    companion object {
        fun newInstance() = SummeryFragment()
    }

    private lateinit var mainActivity: MainActivity
    private lateinit var municipalityMap:MutableMap<String, Municipality>

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

        Log.e("MuniMap", municipalityMap.keys.toString())

        summery()
    }

    private fun summery() {
        val sharedpref = PreferenceManager.getDefaultSharedPreferences(context)


        val dateFromTO = sharedpref.getString("START_DATE", "20190902") + " - " +
                sharedpref.getString("END_DATE", "20191231")
        val municipality = sharedpref.getString("YOUR_RESIDENT", "STOCKHOLM").toString()

        textviewSummeryMunicipality.text = municipality
        textviewSummeryStudyDate.text = dateFromTO
        textviewSummeryTaxTable.text = municipalityMap[municipality]?.municipalityTaxTable().toString()
    }
}
