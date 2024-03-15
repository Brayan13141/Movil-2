package com.example.marsphotos.data

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.ListenableWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.marsphotos.BDLOCAL.BD.EntityDetalles
import com.example.marsphotos.BDLOCAL.REPO.REPOSUSPEND
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.WORKS.BD.WorkerLOGIN_INSERTARBD
import com.example.marsphotos.WORKS.SICE.WorkerCarga
import com.example.marsphotos.WORKS.SICE.WorkerLOGIN
import com.example.marsphotos.model.ALUMNO
import com.example.marsphotos.model.Calificaciones
import com.example.marsphotos.model.CargaAcademicaItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class VIEWLOGIN (
    REPO : REPOSICE,
    viewModelLocal: ViewModelLocal,
    private val context: Context
) : ViewModel() {
    var bandera by mutableStateOf(0)
    var AL by mutableStateOf(ALUMNO(1,"","","",""))
    var Ncontrol by mutableStateOf("S20120185")
    var Contraseña by mutableStateOf("P%o48D_")
    var listaCarga = listOf<CargaAcademicaItem>()
    var listaCalificacion = listOf<Calificaciones>()
    val CON = context
    //val repoBD = REPOBD
    val  viewLocal = viewModelLocal
    fun fNcontrol(Usuario: String ){
        this.Ncontrol = Usuario
    }
    fun fContraseña(contraseña: String){
        this.Contraseña = contraseña
    }
    fun ViewModelAlumno(AL:ALUMNO)
    {
        this.AL = AL
    }
    fun OBTENERCARGA() : Boolean
    {
        try {
            val workManager = WorkManager.getInstance(CON)
            val CARGAWorkRequest = OneTimeWorkRequest.Builder(WorkerCarga::class.java)
                .build()
            workManager.beginUniqueWork("CARGA_Worker", ExistingWorkPolicy.REPLACE, CARGAWorkRequest)
            workManager.enqueue(CARGAWorkRequest)
            WorkManager.getInstance(CON).getWorkInfoByIdLiveData(CARGAWorkRequest.id)
                .observeForever { workInfo ->
                    if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                        val result = workInfo.outputData
                        val cargaAcademicaString = result.getString("CARGA")

                        Log.d("cargaAcademicaString",cargaAcademicaString.toString())
                        var Tamres = cargaAcademicaString.toString().length
                        var count =  1
                        var a = mutableListOf<CargaAcademicaItem>()
                        while (count < Tamres-1)
                        {
                            val objetoA = des(cargaAcademicaString.toString().substring(count,Tamres-1))
                            count = count + objetoA.INDEX
                            if(objetoA.CARGA.Materia!="")
                            {
                                a.add(objetoA.CARGA)
                            }
                            Log.d("CARGA CON WORK",a.toString())
                        }
                        CARGA(a)
                        Log.d("CARGA CON WORK",a.toString())

                    }
                }
            return true
        }catch (e: Exception) {
            Log.e("VIEWMODEL", "Error durante el inicio de sesión: ${e.message}", e)
            ListenableWorker.Result.failure()
            return false
        }
    }
    fun ingresar(U:String,C:String) : Boolean
    {
        var AL1 = ALUMNO(null,"","","","")
        try {
            val workManager = WorkManager.getInstance(CON)
            val data = Data.Builder()
                .putString("username", U)
                .putString("password", C)
                .build()
            val loginWorkRequest = OneTimeWorkRequest.Builder(WorkerLOGIN::class.java)
                .setInputData(data)
                .build()

            workManager.beginUniqueWork("Login_Worker", ExistingWorkPolicy.REPLACE, loginWorkRequest)
            workManager.enqueue(loginWorkRequest) .result.addListener(Runnable {
                CoroutineScope(Dispatchers.Main).launch {
                    Toast.makeText(context,"SE INICIO CON DATOS DEL SERVIDOR",Toast.LENGTH_SHORT).show()
                }
            }, Executors.newSingleThreadExecutor())
                WorkManager.getInstance(CON).getWorkInfoByIdLiveData(loginWorkRequest.id)
                .observeForever { workInfo ->
                    if (workInfo != null && workInfo.state == WorkInfo.State.SUCCEEDED) {
                        val result = workInfo.outputData
                        val matricula = result.getString("matricula")
                        val contrasenia = result.getString("contrasenia")
                        val acceso = result.getString("acceso")
                        val estatus = result.getString("estatus")
                        AL1 =ALUMNO(1,matricula.toString(),contrasenia.toString(),estatus.toString(),acceso.toString())
                        ViewModelAlumno(AL1)
                        bandera=1
//--------------------------------------------------------INSERTA EN BD---------------------------------------------------------
                        viewLocal.guardarDetalles(AL1)
                    }
                }
            return true
        }catch (e: Exception) {
            Log.e("VIEWMODEL", "Error durante el inicio de sesión: ${e.message}", e)
            ListenableWorker.Result.failure()
            return false
        }
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
                val R2 = app.container2.REPOLOCAL
               // val W = app.container3.WORK
                VIEWLOGIN(REPO = R,
                    ViewModelLocal(R2,this.MarsPhotosApplication().applicationContext),this.MarsPhotosApplication().applicationContext)

            }
        }
    }
}