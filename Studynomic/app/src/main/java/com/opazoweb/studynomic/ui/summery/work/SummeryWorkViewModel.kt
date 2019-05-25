package com.opazoweb.studynomic.ui.summery.work

import androidx.lifecycle.ViewModel;
import com.opazoweb.studynomic.data.repository.MunicipalityRepository
import com.opazoweb.studynomic.internal.lazyDeferred

class SummeryWorkViewModel(
    private val municipalityRepository: MunicipalityRepository
) : ViewModel() {


    val tax by lazyDeferred {
        municipalityRepository.getCurrentTax()
    }
}
