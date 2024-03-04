package com.example.marsphotos.data

import android.util.Log
import com.example.marsphotos.model.ALUMNO
import com.example.marsphotos.model.CargaAcademicaItem
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface REPO{
    suspend fun Login(matricula: String, password: String): ALUMNO
    suspend fun CargaAcademicaByAlumno(): String
}
class Iniciar(
    val Login : InterLogin,
    val CARGA : CargaAcademicaByAlumno,
    var XMLA : String = XMLAcceso.trimIndent(),
    var XMLC : String = XMLCARGA.trimIndent()
) : REPO
{
    override suspend fun Login(matricula: String, password: String): ALUMNO {
        try {
            val PETICION = XMLA.format(matricula, password).toRequestBody()
            Login.Cookies()

            var respuesta = Login.Login(PETICION).string().split("{", "}")
            //Log.d("RESPUESTA", respuesta.toString())

            if (respuesta != null) {
                val result = Gson().fromJson("{" + respuesta[1] + "}", ALUMNO::class.java)
              //  Log.d("RESPUESTA", result.toString())
                return result
            }
        } catch (e: Exception) {
            // Manejo de la excepción. Puedes imprimir un mensaje de registro o realizar otras acciones.
            Log.e("ERROR", "Error durante el proceso de login: ${e.message}", e)
        }

        // Si hay algún error, o la respuesta es nula, devuelve un objeto ALUMNO con valores por defecto.
        return ALUMNO("", "", "", "")
    }
    override suspend fun CargaAcademicaByAlumno(): String {
        try {
            val PETICION = XMLC.toRequestBody()
            var lista = mutableListOf<CargaAcademicaItem>()
            var respuesta = CARGA.CARGA(PETICION).string().split("{", "}")
            Log.d("RESPUESTA", respuesta.toString())

            if (respuesta != null) {
             var Tamres = respuesta.toString().length
                var count =  1
                Log.d("-------- ", respuesta.toString().indexOf("Semipresencial").toString()+ " TAMAÑO "+ respuesta.toString().indexOf(", ,").toString())

                     val objetoA = des(respuesta.toString().substring(count,Tamres-10))

                    Log.d("OBJ ", objetoA.toString())

               // Log.d("RESULTADO", lista.toString())

                return respuesta.toString()
            }
        } catch (e: Exception) {
            // Manejo de la excepción. Puedes imprimir un mensaje de registro o realizar otras acciones.
            Log.e("ERROR", "Error durante el proceso de carga académica: ${e.message}", e)
        }

        // Si hay algún error, o la respuesta es nula, devuelve una cadena de resultado por defecto.
        return "result"
    }

}
data class OBJ(val CARGA: CargaAcademicaItem, val INDEX: Int)
fun des(Respuesta: String):OBJ {
    val OBJ = Respuesta.substring(Respuesta.indexOf("Semipresencial"), Respuesta.indexOf(", ,"))
    Log.d("FORMATOO","{\""+OBJ+"}")
    try {

        val result = Gson().fromJson("{\""+OBJ+"}", CargaAcademicaItem::class.java)
        Log.d("RESULTADO",result.toString())
        return OBJ(result ,Respuesta.indexOf(", ,"))
    } catch (e: Exception) {
        Log.e("RESPUESTA DES", "Error al convertir JSON: ${e.message}")
        return OBJ(CargaAcademicaItem(),0)
    }
}


val XMLAcceso = """
    <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:xsd="http://www.w3.org/2001/XMLSchema"
        xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
      <soap:Body>
        <accesoLogin xmlns="http://tempuri.org/">
          <strMatricula>%s</strMatricula>
          <strContrasenia>%s</strContrasenia>
          <tipoUsuario>ALUMNO</tipoUsuario>
        </accesoLogin>
      </soap:Body>
    </soap:Envelope>
"""
const val XMLCARGA =""" 
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <getCargaAcademicaByAlumno xmlns="http://tempuri.org/" />
  </soap:Body>
</soap:Envelope>"""
interface InterLogin {
    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/accesoLogin\"",
        )
    @POST("ws/wsalumnos.asmx")
  suspend fun Login(@Body requestBody: RequestBody): ResponseBody

    @GET("ws/wsalumnos.asmx")
    suspend fun Cookies(): ResponseBody
}
interface CargaAcademicaByAlumno
{
    @Headers(  "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getCargaAcademicaByAlumno\"")

    @POST("ws/wsalumnos.asmx")
    suspend fun CARGA(@Body requestBody: RequestBody): ResponseBody

}