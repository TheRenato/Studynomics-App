package com.opazoweb.studynomic

import android.app.Application
import com.opazoweb.studynomic.data.db.MunicipalityDatabase
import com.opazoweb.studynomic.data.network.*
import com.opazoweb.studynomic.data.repository.MunicipalityRepository
import com.opazoweb.studynomic.data.repository.MunicipalityRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class StudynomicApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@StudynomicApplication))

        bind() from singleton { MunicipalityDatabase(instance()) }
        bind() from singleton { instance<MunicipalityDatabase>().municipalityTaxDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { MunicipalityApi(instance()) }
        bind<SkatteverketNetworkDataSource>() with singleton { SkatteverketNetworkDataSourceImpl(instance()) }
        bind<MunicipalityRepository>() with singleton { MunicipalityRepositoryImpl(instance(), instance()) }

    }
}