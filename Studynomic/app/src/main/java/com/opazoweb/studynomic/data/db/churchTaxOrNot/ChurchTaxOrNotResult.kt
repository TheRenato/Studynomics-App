package com.opazoweb.studynomic.data.db.churchTaxOrNot

interface ChurchTaxOrNotResult {
        val begravningsAvgift: Double
        val forsamling: String
        val kommun: String
        val taxSum: Double
        val year: Int
}