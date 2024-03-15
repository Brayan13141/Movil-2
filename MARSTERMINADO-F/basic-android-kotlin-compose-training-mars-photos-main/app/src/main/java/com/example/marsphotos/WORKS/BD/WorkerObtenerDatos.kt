package com.example.marsphotos.WORKS.BD

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.marsphotos.MarsPhotosApplication


class WorkerObtenerDatos(ctx: Context, params: WorkerParameters,
) : CoroutineWorker(ctx, params) {
    val para = params.inputData
    val context = ctx
    var repolocal = (ctx.applicationContext as MarsPhotosApplication).container2.REPOLOCAL
    override suspend fun doWork(): Result {
        return try {

            val alumno = repolocal.ObtenerDetalles(1)

            Log.e("WORKER ALUMNO", alumno.toString())
            if (alumno != null) {
                // Inicio de sesión correcto: realizar las tareas en segundo plano necesarias según la lógica de su aplicación
                  var Al = workDataOf(
                    "matricula" to alumno.Ncontrol,
                    "contrasenia" to alumno.Contraseña,
                    "acceso" to alumno.Acceso,
                    "estatus" to alumno.Estado,)
               /* Toast.makeText(context,"SE INICIO CON DATOS DE LA BASE DE DATOS", Toast.LENGTH_SHORT).show()
                Toast.makeText(context,"AUUMNO :"+Al.toString(), Toast.LENGTH_SHORT).show()*/
                Result.success(Al)
            } else {
                // Error de inicio de sesión: manejar el error de forma adecuada
                Log.e( "ERROR", "Error de inicio de sesión para")
                Result.failure()
            }
        } catch (e: Exception) {
            Log.e("ERROR", "Error durante el inicio de sesión: ${e.message}", e)
            Result.failure()
        }
    }
}
