package com.opazoweb.studynomic.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.opazoweb.studynomic.data.db.churchTaxOrNot.ChurchTaxResult
import com.opazoweb.studynomic.data.db.churchTaxOrNot.NoChurchTaxResult
import com.opazoweb.studynomic.data.db.entity.CURRENT_MUNICPALITY_ID
import com.opazoweb.studynomic.data.db.entity.MunicipalityResult

@Dao
interface MunicipalityTaxDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(municipalityResult: List<MunicipalityResult>)

    @Query("select * from municipality_tax where id = $CURRENT_MUNICPALITY_ID")
    fun getChurchTaxResult(): LiveData<ChurchTaxResult>

    @Query("select * from municipality_tax where id = $CURRENT_MUNICPALITY_ID")
    fun getNoChurchTaxResult(): LiveData<NoChurchTaxResult>
}