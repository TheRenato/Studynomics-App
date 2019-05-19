package com.opazoweb.studynomic

import java.net.URL


class SweSkatteverket(private var apiURL: String = "") {

    fun getMunicipality() {
        return
    }

    fun searchMunicipalityList (cityKeyword: String) {
        val regExpForUrl: String = "%5E"
        val limit = 500
        val offset = 0
        var year1 = 2019


        apiURL =
            "https://skatteverket.entryscape.net/rowstore/dataset/c67b320b-ffee-4876-b073-dd9236cd2a99?kommun=" +
                    regExpForUrl + cityKeyword.toUpperCase() +
                    "&%C3%A5r=" + year1 +
                    "&_limit=" + limit +
                    "&_offset=" + offset


        val result = URL(apiURL).readText()
        println(result)

    }

    fun getTaxTabells (choosenCity: String) {

    }
}