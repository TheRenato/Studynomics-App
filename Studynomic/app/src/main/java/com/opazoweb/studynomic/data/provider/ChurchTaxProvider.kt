package com.opazoweb.studynomic.data.provider

import com.opazoweb.studynomic.internal.ChurchTaxSystem

interface ChurchTaxProvider {
    fun getChurchTaxSystem(): ChurchTaxSystem
}