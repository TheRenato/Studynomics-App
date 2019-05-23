package com.opazoweb.studynomic.data.network

import androidx.lifecycle.LiveData
import com.opazoweb.studynomic.data.network.response.MunicipalitySkatteverketResponse

class SkatteverketNetworkDataSourceImpl(
    private val municipalityApi: MunicipalityApi
) : SkatteverketNetworkDataSource {

    override val downloadMunicipalityTax: LiveData<MunicipalitySkatteverketResponse>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override suspend fun fetchMunicipalityTax(city: String) {
        try {
            val fetchMunicipalityTax = municipalityApi
                .getCurrentTax(city)
                .await()
        }
    }
}