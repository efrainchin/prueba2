package com.efrain.pruebaalbo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.efrain.pruebaalbo.model.Beers


/**
 * Created by Carlos chin on 02/01/2021.
 * Correo: efrain@sunwise.io
 */

@Dao
interface BeersDao {

    @Query("SELECT * FROM beers")
    suspend fun getAll(): List<Beers>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserBeer(vararg items: Beers)
}