package com.opazoweb.studynomic.ui.summery.all

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.opazoweb.studynomic.R

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
        // TODO: Use the ViewModel
    }

}
