package com.opazoweb.studynomic3.ui.summery

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager

import com.opazoweb.studynomic3.R
import com.opazoweb.studynomic3.data.location.MunicipalityCollector
import com.opazoweb.studynomic3.ui.MainActivity
import kotlinx.android.synthetic.main.summery_fragment.*
import kotlin.coroutines.coroutineContext

class SummeryFragment : Fragment() {

    companion object {
        fun newInstance() = SummeryFragment()
    }
    private val current: Context? = activity?.applicationContext
    private val municipalityCollector = MunicipalityCollector(current)
    private val municipalityMap = municipalityCollector.getMunicipalityMap()


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

        summery()
    }

    private fun summery() {
        val sharedpref = PreferenceManager.getDefaultSharedPreferences(context)


        val dateFromTO = sharedpref.getString("START_DATE", "NULL") + " - " +
                sharedpref.getString("END_DATE", "NULL")
        val municipality = sharedpref.getString("YOUR_RESIDENT", "NULL").toString()

        textviewSummeryMunicipality.text = municipality
        textviewSummeryStudyDate.text = dateFromTO
        textviewSummeryTaxTable.text = municipalityMap[municipality]?.municipalityTaxTable().toString()
    }
}
