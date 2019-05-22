package com.opazoweb.studynomic.ui.summery.work

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.opazoweb.studynomic.R
import com.opazoweb.studynomic.data.Skatteverket.municipality.MunicipalityApi
import kotlinx.android.synthetic.main.summery_work_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SummeryWorkFragment : Fragment() {

    companion object {
        fun newInstance() = SummeryWorkFragment()
    }

    private lateinit var viewModel: SummeryWorkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.summery_work_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SummeryWorkViewModel::class.java)
        // TODO: Use the ViewModel

        val municipalityApi = MunicipalityApi()

        GlobalScope.launch(Dispatchers.Main) {
            val municipalitySkatteverketResponse = municipalityApi.getCurrentTax(city = "^KÖP").await()
            summeryWorkFragmentText.text = municipalitySkatteverketResponse.results.toString()

        }
    }

}
