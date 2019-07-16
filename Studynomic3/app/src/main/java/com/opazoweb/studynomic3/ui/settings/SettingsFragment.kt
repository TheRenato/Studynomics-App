package com.opazoweb.studynomic3.ui.settings

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.opazoweb.studynomic3.R
import com.opazoweb.studynomic3.data.location.Municipality
import com.opazoweb.studynomic3.ui.MainActivity

class SettingsFragment : PreferenceFragmentCompat() {


    private lateinit var mainActivity: MainActivity
    private lateinit var municipalityMap:MutableMap<String, Municipality>
    private lateinit var defaultMunicipality: String

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        mainActivity = (activity as MainActivity)
        municipalityMap = mainActivity.getMunicipalityMap()
        defaultMunicipality = getString(R.string.default_Municipalety)


        val listTownship: ListPreference = findPreference("YOUR_TOWNSHIP") as ListPreference

        listTownship.entries = municipalityMap[defaultMunicipality]?.townshipArray()
        listTownship.entryValues = municipalityMap[defaultMunicipality]?.townshipArray()
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

    }

    private fun listTownshipOf () {
        val municipalitys = findPreference("YOUR_RESIDENT") as ListPreference
        val choosenMunicipality = municipalitys.entry.toString()

        val listTownship: ListPreference = findPreference("YOUR_TOWNSHIP") as ListPreference

        listTownship.entries = municipalityMap[choosenMunicipality]?.townshipArray()
        listTownship.entryValues = municipalityMap[choosenMunicipality]?.townshipArray()

    }
}