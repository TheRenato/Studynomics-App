package com.opazoweb.studynomic

import android.app.Application
import android.preference.Preference
import androidx.preference.PreferenceManager
import com.jakewharton.threetenabp.AndroidThreeTen
import com.opazoweb.studynomic.data.db.MunicipalityDatabase
import com.opazoweb.studynomic.data.network.*
import com.opazoweb.studynomic.data.provider.ChurchTaxProvider
import com.opazoweb.studynomic.data.provider.ChurchTaxProviderImpl
import com.opazoweb.studynomic.data.repository.MunicipalityRepository
import com.opazoweb.studynomic.data.repository.MunicipalityRepositoryImpl
import com.opazoweb.studynomic.ui.summery.work.SummeryWorkViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class StudynomicApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@StudynomicApplication))

        bind() from singleton { MunicipalityDatabase(instance()) }
        bind() from singleton { instance<MunicipalityDatabase>().municipalityTaxDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { MunicipalityApi(instance()) }
        bind<SkatteverketNetworkDataSource>() with singleton { SkatteverketNetworkDataSourceImpl(instance()) }
        bind<MunicipalityRepository>() with singleton { MunicipalityRepositoryImpl(instance(), instance() ) }
        bind<ChurchTaxProvider>() with singleton { ChurchTaxProviderImpl(instance()) }
        bind() from provider { SummeryWorkViewModelFactory(instance(), instance()) }

    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}