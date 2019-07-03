package com.opazoweb.studynomic3.ui.csn

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.opazoweb.studynomic3.R

class CsnSummeryFragment : Fragment() {

    companion object {
        fun newInstance() = CsnSummeryFragment()
    }

    private lateinit var viewModel: CsnSummeryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.csn_summery_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CsnSummeryViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
