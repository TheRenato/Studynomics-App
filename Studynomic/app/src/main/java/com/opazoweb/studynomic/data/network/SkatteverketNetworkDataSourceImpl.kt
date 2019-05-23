package com.opazoweb.studynomic.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.opazoweb.studynomic.data.network.response.MunicipalitySkatteverketResponse
import com.opazoweb.studynomic.internal.NoConnectivityException

class SkatteverketNetworkDataSourceImpl(
    private val municipalityApi: MunicipalityApi
) : SkatteverketNetworkDataSource {

    private val _downloadMunicipalityTax = MutableLiveData<MunicipalitySkatteverketResponse>()
    override val downloadMunicipalityTax: LiveData<MunicipalitySkatteverketResponse>
        get() = _downloadMunicipalityTax

    override suspend fun fetchMunicipalityTax(city: String) {
        try {
            val fetchMunicipalityTax = municipalityApi
                .getCurrentTax(city)
                .await()
            _downloadMunicipalityTax.postValue(fetchMunicipalityTax)
        }
        catch (e: NoConnectivityException) {
            Log.e( "Connectivity", "No internet connection", e)
        }
    }
}