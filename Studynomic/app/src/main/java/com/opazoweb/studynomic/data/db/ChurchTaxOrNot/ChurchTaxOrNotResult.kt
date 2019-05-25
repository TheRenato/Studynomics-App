package com.opazoweb.studynomic.data.db.ChurchTaxOrNot

interface ChurchTaxOrNotResult {
        val begravningsAvgift: Double
        val forsamling: String
        val kommun: String
        val taxSum: Double
        val year: Int
}