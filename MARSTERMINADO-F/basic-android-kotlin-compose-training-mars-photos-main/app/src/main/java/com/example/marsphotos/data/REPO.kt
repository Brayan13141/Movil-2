package com.example.marsphotos.data

import android.util.Log
import com.example.marsphotos.model.ALUMNO
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
    suspend fun Info(): String
}
class Iniciar(
    val Login : InterLogin,
    val info : InterInfo,
    var XMLA : String = XMLAcceso.trimIndent()
) : REPO
{
    override suspend fun Login(matricula: String, password: String): ALUMNO {
        val PETICION = XMLA.toRequestBody()
        //Log.d("PETICION XML-REQUEST",PETICION.toString())
        Login.Cookies()

        var respuesta = Login.Login(PETICION).string().split("{","}")
        Log.d("RESPUESTA",respuesta.toString())
        if(respuesta !=null)
        {
           val result = Gson().fromJson("{"+respuesta[1] +"}", ALUMNO::class.java)
            Log.d("RESPUESTA", result.toString())
            //Log.d("RESPUESTA", result.Login.toString)
            return result;
        }
        return ALUMNO("","","","",);
    }

    override suspend fun Info(): String {
        TODO("Not yet implemented")
    }
}


 var XMLAcceso = """
                <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                    xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                    xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                  <soap:Body>
                    <accesoLogin xmlns="http://tempuri.org/">
                      <strMatricula>s20120185</strMatricula>
                      <strContrasenia>P%o48D_</strContrasenia>
                      <tipoUsuario>ALUMNO</tipoUsuario>
                    </accesoLogin>
                  </soap:Body>
                </soap:Envelope>
            """

const val XMLPerfil = """<soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                            xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                            xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                            <soap:Body>
                                <getAlumnoAcademicoWithLineamiento xmlns="http://tempuri.org/" />
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
interface InterInfo  {

    @Headers("Content-Type: text/xml")
    @POST("ws/wsalumnos.asmx")
    suspend fun Perfil(@Body requestBody: RequestBody): ResponseBody
}

