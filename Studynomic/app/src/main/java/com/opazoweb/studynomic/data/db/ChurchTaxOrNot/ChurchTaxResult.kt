package com.opazoweb.studynomic.data.db.ChurchTaxOrNot

import androidx.room.ColumnInfo

class ChurchTaxResult(
    @ColumnInfo(name = "begravningsAvgift")
    override val begravningsAvgift: Double,
    @ColumnInfo(name = "forsamling")
    override val forsamling: String,
    @ColumnInfo(name = "kommun")
    override val kommun: String,
    @ColumnInfo(name = "summaInklKyrkoavgift")
    override val taxSum: Double,
    @ColumnInfo(name = "year")
    override val year: Int
): ChurchTaxOrNotResult