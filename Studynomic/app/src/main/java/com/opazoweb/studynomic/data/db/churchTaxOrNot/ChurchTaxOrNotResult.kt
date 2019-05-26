package com.opazoweb.studynomic.data.db.churchTaxOrNot

interface ChurchTaxOrNotResult {
        val burialFee: Double
        val township: String
        val municipality: String
        val taxSum: Double
        val year: Int
}