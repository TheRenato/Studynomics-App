package com.opazoweb.studynomic3.ui.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.opazoweb.studynomic3.R
import com.opazoweb.studynomic3.data.location.MunicipalityCollector

class SettingsFragment : PreferenceFragmentCompat() {


    private val current = requireContext()
    private val municipalityCollector = MunicipalityCollector(current)
    private val municipalityMap = municipalityCollector.getMunicipalityMap()
    private val default_Municipalety: String = R.string.default_Municipalety.toString()

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
        val listTownship: ListPreference = findPreference("YOUR_TOWNSHIP") as ListPreference

        listTownship.entries = municipalityMap?.get(default_Municipalety)?.townshipArray()
        listTownship.entryValues = municipalityMap?.get(default_Municipalety)?.townshipArray()
        listTownship.setOnPreferenceClickListener {
            listTownshipOf()
            true
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = getString(R.string.settings)
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null


        listTownshipOf()
//        setPreferencesFromResource(5, null)
    }

    private fun listTownshipOf () {
        val municipalitys = findPreference("YOUR_RESIDENT") as ListPreference
        val choosenMunicipality = municipalitys.entry.toString()

        val listTownship: ListPreference = findPreference("YOUR_TOWNSHIP") as ListPreference

        listTownship.entries = municipalityMap[choosenMunicipality]?.townshipArray()
        listTownship.entryValues = municipalityMap[choosenMunicipality]?.townshipArray()

    }
}