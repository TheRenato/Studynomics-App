package com.opazoweb.studynomic.ui.summery.work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.opazoweb.studynomic.data.provider.ChurchTaxProvider
import com.opazoweb.studynomic.data.repository.MunicipalityRepository


class SummeryWorkViewModelFactory(
    private val municipalityRepository: MunicipalityRepository,
    private val churchTaxProvider: ChurchTaxProvider
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SummeryWorkViewModel(municipalityRepository, churchTaxProvider) as T
    }
}