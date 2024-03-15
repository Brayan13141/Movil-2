package com.example.marsphotos.BDLOCAL.BD

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Detalles")
data class EntityDetalles(
    @PrimaryKey
    val id: Int?,
    var Ncontrol : String = "",
    var Contraseña : String? = null,
    var Estado : String = "",
    var Acceso : String = ""
)