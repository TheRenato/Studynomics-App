package com.opazoweb.studynomic.ui.summery.work

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.opazoweb.studynomic.data.repository.MunicipalityRepository


class SummeryWorkViewModelFactory(
    private val municipalityRepository: MunicipalityRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SummeryWorkViewModel(municipalityRepository) as T
    }
}