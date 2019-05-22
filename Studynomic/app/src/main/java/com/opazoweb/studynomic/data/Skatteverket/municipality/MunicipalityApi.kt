package com.opazoweb.studynomic.data.Skatteverket.municipality

import androidx.room.Query
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import java.time.Year

interface MunicipalityApi {

    @GET("c67b320b-ffee-4876-b073-dd9236cd2a99")
    fun getCurrentTax(
        @Query("kommun") city: String,
        @Query("år") year: Int =  2019
    ):Deferred<Result>

    companion object{
        operator fun invoke(): MunicipalityApi {
            val requestInterceptor = Interceptor{chain ->

                val request = chain.request()
                    .newBuilder()
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://skatteverket.entryscape.net/rowstore/dataset/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MunicipalityApi::class.java)

        }
    }
}