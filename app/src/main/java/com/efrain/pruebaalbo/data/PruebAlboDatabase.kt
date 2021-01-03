package com.efrain.pruebaalbo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.efrain.pruebaalbo.data.dao.BeersDao
import com.efrain.pruebaalbo.model.Beers
import com.efrain.pruebaalbo.utils.Constants.Companion.DB_NAME
import com.efrain.pruebaalbo.utils.Constants.Companion.DB_VERSION


/**
 * Created by Carlos chin on 02/01/2021.
 * Correo: efrain@sunwise.io
 */

@Database(entities = [Beers::class], version = DB_VERSION, exportSchema = false)
abstract class PruebAlboDatabase: RoomDatabase() {

    abstract fun getUserDao(): BeersDao

    companion object {

        @Volatile
        private var INSTANCE: PruebAlboDatabase? = null

        fun getInstance(context: Context): PruebAlboDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, PruebAlboDatabase::class.java, DB_NAME
                ).fallbackToDestructiveMigration().allowMainThreadQueries()
                        .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}