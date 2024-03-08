package com.example.marsphotos.BDLOCAL.REPO

import com.example.marsphotos.BDLOCAL.BD.EntityDetalles

interface REPOSUSPEND{
    suspend fun InsertarDetalles(item: EntityDetalles)
    suspend fun deleteItem(item: EntityDetalles)
    suspend fun ActualizarDetalles(item: EntityDetalles)

    suspend fun ObtenerDetalles(ID:Int):EntityDetalles
}