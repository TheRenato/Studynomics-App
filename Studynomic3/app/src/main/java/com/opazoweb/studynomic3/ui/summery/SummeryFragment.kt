package com.opazoweb.studynomic3.ui.summery

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager

import com.opazoweb.studynomic3.R
import kotlinx.android.synthetic.main.summery_fragment.*

class SummeryFragment : Fragment() {

    companion object {
        fun newInstance() = SummeryFragment()
    }

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

        textviewSummeryMunicipality.text = sharedpref.getString("YOUR_RESIDENT", "NULL")
        textviewSummeryStudyDate.text = dateFromTO
    }
}
