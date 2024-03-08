package com.example.marsphotos.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Entity
import com.example.marsphotos.BDLOCAL.BD.EntityDetalles
import com.example.marsphotos.BDLOCAL.REPO.REPOSUSPEND
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.model.ALUMNO
import com.example.marsphotos.model.DETALLESALUMNO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ViewModelLocal (val REPOLOCAL : REPOSUSPEND) : ViewModel() {
    var UIDESTALLES by mutableStateOf(ALUMNO(null,"","","",""))
    fun Modelo_Entidad(ALUMNO : ALUMNO) : EntityDetalles
    {
        var Det = EntityDetalles(1,"","","","")
        Det.Ncontrol = ALUMNO.matricula
        Det.Contraseña = ALUMNO.contrasenia
        Det.Estado = ALUMNO.estatus
        Det.Acceso = ALUMNO.acceso
        return Det
    }
    fun Entidad_Modelo(ALUMNO : EntityDetalles) : ALUMNO
    {
        var Det = ALUMNO(1,"","","","")
        Det.matricula = ALUMNO.Ncontrol
        Det.contrasenia = ALUMNO.Contraseña
        Det.estatus = ALUMNO.Estado
        Det.acceso = ALUMNO.Acceso
        return Det
    }
    fun guardarDetalles(ALUMNO : ALUMNO) {
        runBlocking {
            launch(Dispatchers.IO) {
                try {
                    UIDESTALLES = ALUMNO
                    REPOLOCAL.InsertarDetalles(Modelo_Entidad(ALUMNO))
                    //Log.e("", "SE INSERTO EL ALUMNO")

                }catch (e: Exception) {
                    Log.e("ERROR", "Error durante el proceso guardar detalles: ${e.message}", e)
                }
            }
        }

    }
    fun ObtenerDetalles(id:Int)  : ALUMNO
    {
         runBlocking {
             launch(Dispatchers.IO) {
                 try {

                     UIDESTALLES = Entidad_Modelo(REPOLOCAL.ObtenerDetalles(id))

                 }catch (e: Exception) {
                     Log.e("ERROR", "Error durante el proceso obtener detalles: ${e.message}", e)
                 }
             }
         }
        return UIDESTALLES
    }













    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MarsPhotosApplication)
                val R = app.container2.REPOLOCAL
                ViewModelLocal(R)
            }
        }
    }
}