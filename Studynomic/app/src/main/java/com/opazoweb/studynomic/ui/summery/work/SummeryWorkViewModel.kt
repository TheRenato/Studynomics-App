package com.opazoweb.studynomic.ui.summery.work

import androidx.lifecycle.ViewModel;
import com.opazoweb.studynomic.data.repository.MunicipalityRepository
import com.opazoweb.studynomic.internal.ChurchTaxSystem
import com.opazoweb.studynomic.internal.lazyDeferred

class SummeryWorkViewModel(
    private val municipalityRepository: MunicipalityRepository
) : ViewModel() {

    private val churchTaxSystem = ChurchTaxSystem.ChurchTax

    val isChurchTax: Boolean
        get() = churchTaxSystem == ChurchTaxSystem.ChurchTax


    val tax by lazyDeferred {
        municipalityRepository.getCurrentTax(isChurchTax)
    }
}
