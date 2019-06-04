package com.opazoweb.studynomic.data.network


import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.opazoweb.studynomic.data.network.response.MunicipalitySkatteverketResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.threeten.bp.ZonedDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface MunicipalityApi {

    @GET("c67b320b-ffee-4876-b073-dd9236cd2a99")
    fun getCurrentTax(
        @Query("kommun") city: String,
        @Query("församling") township: String = "%E5",
        @Query("år") year: Int = ZonedDateTime.now().year

    ):Deferred<MunicipalitySkatteverketResponse>

    companion object{
        operator fun invoke(
            connectivityInterceptor: ConnectivityInterceptor
        ): MunicipalityApi {
            val requestInterceptor = Interceptor{chain ->

                val request = chain.request()
                    .newBuilder()
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
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