package com.opazoweb.studynomic.data.repository

import androidx.lifecycle.LiveData
import com.opazoweb.studynomic.data.db.MunicipalityTaxDao
import com.opazoweb.studynomic.data.db.churchTaxOrNot.ChurchTaxOrNotResult
import com.opazoweb.studynomic.data.network.SkatteverketNetworkDataSource
import com.opazoweb.studynomic.data.network.response.MunicipalitySkatteverketResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class MunicipalityRepositoryImpl (
    private val municipalityTaxDao: MunicipalityTaxDao,
    private val skatteverketNetworkDataSource: SkatteverketNetworkDataSource
): MunicipalityRepository {

    init {
        skatteverketNetworkDataSource.downloadMunicipalityTax.observeForever { newMunicipalityTax ->
            persistFetchedMunicipalityTax(newMunicipalityTax)
        }
    }

    override suspend fun getCurrentTax(church: Boolean): LiveData<out ChurchTaxOrNotResult> {
        return withContext(Dispatchers.IO) {
            initTaxData()
            return@withContext if (church) municipalityTaxDao.getChurchTaxResult()
            else municipalityTaxDao.getNoChurchTaxResult()
        }
    }

    private fun persistFetchedMunicipalityTax(fetchedTax: MunicipalitySkatteverketResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            municipalityTaxDao.upsert(fetchedTax.municipalityResult)
        }
    }

    private suspend fun initTaxData() {
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusWeeks(3))) {
            fetchMunicipalityTax()
        }

    }

    private suspend fun fetchMunicipalityTax() {
        skatteverketNetworkDataSource.fetchMunicipalityTax(
            "KÖPING"
        )
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val twoWeeksAgo = ZonedDateTime.now().minusWeeks(2)
        return lastFetchTime.isBefore(twoWeeksAgo)
    }
}