package com.opazowebb.studynomic

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


class SweSkatteverket {

    fun getCity() {
        return
    }

    fun getCityList (cityKeyword: String) {
        val regExpForUrl: String = "%5E"
        val limit = 500
        val offset = 0
        var year = Calendar.getInstance().get(Calendar.YEAR)

        var apiURL = "https://skatteverket.entryscape.net/rowstore/dataset/c67b320b-ffee-4876-b073-dd9236cd2a99?kommun=$regExpForUrl${cityKeyword.toUpperCase()}&%C3%A5r=$year&_limit=$limit&_offset=$offset"



    }
}