package com.example.marsphotos.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.model.ALUMNO
import com.example.marsphotos.model.Calificaciones
import com.example.marsphotos.model.CargaAcademicaItem
import kotlinx.coroutines.launch

class VIEWLOGIN(Acciones: REPO) : ViewModel() {
    var AL by mutableStateOf(ALUMNO("","","",""))
    var Ncontrol by mutableStateOf("S20120185")
    var Contraseña by mutableStateOf("P%o48D_")
    var listaCarga = listOf<CargaAcademicaItem>()
    var listaCalificacion = listOf<Calificaciones>()
    val Acciones =Acciones


    fun fNcontrol(value: String){
        Ncontrol = value
    }
    fun fContraseña(value: String){
        Contraseña = value
    }
    fun IniciarSesion(M : String, C : String) : Boolean
    {
        viewModelScope.launch {
          AL = Acciones.Login(M,C)
         }
        if (AL.matricula != "")
        {
            return true
        }else
        return false
    }
     fun CARGA(Lista : List<CargaAcademicaItem>)
     {
         listaCarga = Lista
     }
    fun CALIFICACION(Lista : List<Calificaciones>)
    {
        listaCalificacion = Lista
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
          initializer {
                val app = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val R = app.container.REP
                VIEWLOGIN(Acciones = R)
          }
        }
    }
}