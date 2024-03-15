package com.example.marsphotos.data

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.marsphotos.BDLOCAL.BD.EntityDetalles
import com.example.marsphotos.BDLOCAL.REPO.REPOSUSPEND
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.WORKS.SICE.WorkerLOGIN
import com.example.marsphotos.model.ALUMNO
import com.example.marsphotos.model.DETALLESALUMNO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

class ViewModelLocal (val REPOLOCAL : REPOSUSPEND,
context: Context
) : ViewModel() {
    var UIDESTALLES by mutableStateOf(ALUMNO(1,"","","",""))
    val CON = context
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
        Det.contrasenia = ALUMNO.Contraseña.toString()
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
                   // Log.e("VIEWMODEL", "SE INSERTO EL ALUMNO" + Modelo_Entidad(ALUMNO).toString())

                }catch (e: Exception) {
                    Log.e("ERROR", "Error durante el proceso guardar detalles: ${e.message}", e)
                }
            }
        }

    }
     fun ObtenerDetalles(id:Int)  : ALUMNO
    {  runBlocking {
        launch(Dispatchers.IO) {
                 try {
                     UIDESTALLES = Entidad_Modelo(REPOLOCAL.ObtenerDetalles(id))
                    //  Toast.makeText(CON, "SE INSERTO: " + UIDESTALLES.toString(), Toast.LENGTH_SHORT).show()
                    Log.d("SE OBTUVO",UIDESTALLES.toString())
                 }catch (e: Exception) {
                     Log.e("ERROR", "Error durante el proceso obtener detalles: ${e.message}", e)
                 }}}
        return UIDESTALLES
    }
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MarsPhotosApplication)
                val R = app.container2.REPOLOCAL
                ViewModelLocal(R,this.MarsPhotosApplication().applicationContext)
            }
        }
    }
}