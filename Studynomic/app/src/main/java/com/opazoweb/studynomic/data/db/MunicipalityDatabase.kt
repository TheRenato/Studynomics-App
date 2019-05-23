package com.opazoweb.studynomic.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.opazoweb.studynomic.data.db.entity.MunicipalityResult



@Database(
    entities = [MunicipalityResult::class],
    version = 1
)
abstract class MunicipalityDatabase : RoomDatabase() {
    abstract fun municipalityTaxDao(): MunicipalityTaxDao

    companion object {
        @Volatile private var instance: MunicipalityDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                    MunicipalityDatabase::class.java, "municipality.db")
                    .build()
    }
}