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
import com.opazoweb.studynomic.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.summery_work_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class SummeryWorkFragment : ScopedFragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: SummeryWorkViewModelFactory by instance()

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

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(SummeryWorkViewModel::class.java)

        bindUI()

    }

    private fun bindUI() = launch {
        val currentTax = viewModel.tax.await()
        currentTax.observe(this@SummeryWorkFragment, Observer {
            if(it == null) return@Observer

            summeryWorkFragmentText.text = it.toString()
        })
    }

}
