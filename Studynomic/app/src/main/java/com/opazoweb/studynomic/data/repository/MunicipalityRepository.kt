package com.opazoweb.studynomic.data.repository

import androidx.lifecycle.LiveData
import com.opazoweb.studynomic.data.db.churchTaxOrNot.ChurchTaxOrNotResult

interface MunicipalityRepository {
    suspend fun getCurrentTax(church: Boolean): LiveData<out ChurchTaxOrNotResult>
}