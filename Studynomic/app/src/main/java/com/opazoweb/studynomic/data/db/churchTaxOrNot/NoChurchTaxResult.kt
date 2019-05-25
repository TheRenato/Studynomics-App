package com.opazoweb.studynomic.data.db.churchTaxOrNot

import androidx.room.ColumnInfo

class NoChurchTaxResult (
    @ColumnInfo(name = "begravningsAvgift")
    override val begravningsAvgift: Double,
    @ColumnInfo(name = "forsamling")
    override val forsamling: String,
    @ColumnInfo(name = "kommun")
    override val kommun: String,
    @ColumnInfo(name = "summaExklKyrkoavgift")
    override val taxSum: Double,
    @ColumnInfo(name = "ar")
    override val year: Int
): ChurchTaxOrNotResult