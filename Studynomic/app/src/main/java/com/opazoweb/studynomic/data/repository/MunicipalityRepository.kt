package com.opazoweb.studynomic.data.repository

import androidx.lifecycle.LiveData
import com.opazoweb.studynomic.data.db.entity.MunicipalityResult

interface MunicipalityRepository {
    suspend fun getCurrentTax(): LiveData<out MunicipalityResult>
}