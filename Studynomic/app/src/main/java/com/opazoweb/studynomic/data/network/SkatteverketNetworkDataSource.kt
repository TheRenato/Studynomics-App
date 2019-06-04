package com.opazoweb.studynomic.data.network

import androidx.lifecycle.LiveData
import com.opazoweb.studynomic.data.network.response.MunicipalitySkatteverketResponse

interface SkatteverketNetworkDataSource {
    val downloadMunicipalityTax: LiveData<MunicipalitySkatteverketResponse>

    suspend fun fetchMunicipalityTax(
        city: String,
        township: String
    )
}