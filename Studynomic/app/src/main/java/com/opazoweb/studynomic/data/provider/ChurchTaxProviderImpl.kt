package com.opazoweb.studynomic.data.provider

import android.content.Context
import android.content.SharedPreferences
import android.preference.Preference
import android.preference.PreferenceManager
import com.opazoweb.studynomic.internal.ChurchTaxSystem

const val CHURCH_FEE = "CHURCH_FEE"

class ChurchTaxProviderImpl(context: Context) : PreferenceProvider(context), ChurchTaxProvider {

    override fun getChurchTaxSystem(): ChurchTaxSystem {
        val selectedName = preference.getString(CHURCH_FEE, ChurchTaxSystem.NoChurchTax.name)

        return ChurchTaxSystem.valueOf(selectedName!!)
    }
}