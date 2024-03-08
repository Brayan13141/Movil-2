package com.example.marsphotos.BDLOCAL.BD

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertarDetalles(item: EntityDetalles)

    @Update
    suspend fun ActualizarDetalles(item: EntityDetalles)

    @Delete
    suspend fun delete(item: EntityDetalles)

    @Query("SELECT * from Detalles WHERE id = :id")
    fun ObtenerDetalles(id: Int): EntityDetalles


}