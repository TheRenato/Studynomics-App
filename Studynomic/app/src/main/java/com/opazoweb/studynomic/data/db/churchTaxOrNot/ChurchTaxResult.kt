package com.opazoweb.studynomic.data.db.churchTaxOrNot

import androidx.room.ColumnInfo

class ChurchTaxResult(
    @ColumnInfo(name = "burialFee")
    override val burialFee: Double,
    @ColumnInfo(name = "township")
    override val township: String,
    @ColumnInfo(name = "municipality")
    override val municipality: String,
    @ColumnInfo(name = "sumInclChurchFee")
    override val taxSum: Double,
    @ColumnInfo(name = "year")
    override val year: Int
): ChurchTaxOrNotResult