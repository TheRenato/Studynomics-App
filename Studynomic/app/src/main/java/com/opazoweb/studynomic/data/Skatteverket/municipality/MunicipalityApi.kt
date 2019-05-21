package com.opazoweb.studynomic.data.Skatteverket.municipality

import androidx.room.Query
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import retrofit2.http.GET
import java.time.Year

interface MunicipalityApi {

    @GET()
    fun getCurrentTax(
        @Query( "kommun") city: String,
        @Query("%C3%A5r") year: Year
    ):Deferred<Result>

    companion object{
        operator fun invoke(): MunicipalityApi {
            val requestInterceptor = Interceptor{chain ->

                val url = chain.request()
                    .url()
                    .newBuilder()
                    .build()
            }
        }
    }
}