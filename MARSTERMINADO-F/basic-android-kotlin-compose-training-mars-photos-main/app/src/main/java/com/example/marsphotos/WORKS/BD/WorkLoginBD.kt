package com.example.marsphotos.WORKS.BD

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.marsphotos.BDLOCAL.BD.EntityDetalles
import com.example.marsphotos.MarsPhotosApplication

private const val TAG = "WORKERLOGIN-BD"

class WorkerLOGIN_INSERTARBD(ctx: Context, params: WorkerParameters,
) : CoroutineWorker(ctx, params) {
    val para = params.inputData

    var RepoLocal = (ctx.applicationContext as MarsPhotosApplication).container2.REPOLOCAL
    override suspend fun doWork(): Result {
        return try {

            var a = EntityDetalles(1,para.getString("M") ?: "",para.getString("C") ?: "",
                  para.getString("E") ?: "",para.getString("A") ?: "")
            Log.d("WORKER_BD ALUMNO", a.toString())
             val alumno = RepoLocal.InsertarDetalles(a) // Llamar a la función Login de REPOSICE
            Log.d("WORKER_BD ALUMNO", alumno.toString())

            if (alumno != null) {
               //  Log.d(TAG, "SE INSERTO EN LA BD :"+alumno.toString())
                Result.success()
            } else {
                // Error de inicio de sesión: manejar el error de forma adecuada
                Log.e(TAG, "Error de inicio de sesión CON LA BD")
                Result.failure()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error de inicio de sesión CON LA BD :${e.message}", e)
            Result.failure()
        }
    }
}
