package com.opazoweb.studynomic.data.provider

import android.content.Context
import android.content.SharedPreferences
import android.preference.Preference
import android.preference.PreferenceManager
import com.opazoweb.studynomic.internal.ChurchTaxSystem

const val CHURCH_FEE = "CHURCH_FEE"

class ChurchTaxProviderImpl(context: Context) : ChurchTaxProvider {
    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    override fun getChurchTaxSystem(): ChurchTaxSystem {
        val selectedName = preference.getString(CHURCH_FEE, ChurchTaxSystem.NoChurchTax.name)

        return ChurchTaxSystem.valueOf(selectedName!!)
    }
}