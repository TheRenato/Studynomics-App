package com.opazoweb.studynomic.ui.summery.work

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.opazoweb.studynomic.R
import com.opazoweb.studynomic.data.network.ConnectivityInterceptorImpl
import com.opazoweb.studynomic.data.network.MunicipalityApi
import com.opazoweb.studynomic.data.network.SkatteverketNetworkDataSourceImpl
import com.opazoweb.studynomic.data.network.response.MunicipalitySkatteverketResponse
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

        val municipalityApi = MunicipalityApi(ConnectivityInterceptorImpl(this.context!!))
        val skatteverketNetworkDataSource = SkatteverketNetworkDataSourceImpl(municipalityApi)

        skatteverketNetworkDataSource.downloadMunicipalityTax.observe(this, Observer {
            summeryWorkFragmentText.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
           skatteverketNetworkDataSource.fetchMunicipalityTax("KÖPING")


        }
    }

}
