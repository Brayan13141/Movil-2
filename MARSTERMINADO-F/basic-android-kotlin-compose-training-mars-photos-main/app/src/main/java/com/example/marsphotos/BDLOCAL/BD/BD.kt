package com.example.marsphotos.BDLOCAL.BD

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database class with a singleton Instance object.
 */
@Database(entities = [EntityDetalles::class], version = 1)
abstract class BD : RoomDatabase() {

    abstract fun DAO(): DAO

    companion object {
        @Volatile
        private var Instance: BD? = null
        fun getDatabase(context: Context): BD {
                return Instance ?: synchronized(this) {
                    Room.databaseBuilder(context, BD ::class.java, "BDLOCAL")
                        .build()
                        .also { Instance = it }
                }

        }
    }
}