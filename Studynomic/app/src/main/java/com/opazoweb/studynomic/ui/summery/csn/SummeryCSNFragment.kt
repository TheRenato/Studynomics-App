package com.opazoweb.studynomic.ui.summery.csn

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.opazoweb.studynomic.R

class SummeryCSNFragment : Fragment() {

    companion object {
        fun newInstance() = SummeryCSNFragment()
    }

    private lateinit var viewModel: SummeryCsnViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.summery_csn_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SummeryCsnViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
