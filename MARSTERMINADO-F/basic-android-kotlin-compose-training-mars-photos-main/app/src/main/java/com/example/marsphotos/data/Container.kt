package com.example.marsphotos.data

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

interface Container {
  val REP : REPO
}

private const val BASE_URL =
    "https://sicenet.surguanajuato.tecnm.mx"

class DefaultContainer: Container
{
    val cliente = OkHttpClient.Builder()

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create()).client(cliente.build())
        .build()

      val REPOLogin: InterLogin by lazy {
        retrofit.create(InterLogin::class.java)
    }

      val REPOinfo: InterInfo by lazy {
        retrofit.create(InterInfo::class.java)
    }
    override val REP: REPO by lazy {
        Iniciar(REPOLogin, REPOinfo)
    }

}

class CookiesInterceptor: Interceptor {

    private var cookies: List<String> = emptyList()

    fun setCookies(cookies: List<String>) {
        this.cookies = cookies
    }
    override fun intercept(IN: Interceptor.Chain): Response {
        var request = IN.request()

        if (cookies!=null) {
            val Header = StringBuilder()
            for (cookie in cookies) {
                if (Header.isNotEmpty()) {
                    Header.append("; ")
                }
                Header.append(cookie)
            }
            request = request.newBuilder()
                .header("Cookie", Header.toString())
                .build()
        }

        val response = IN.proceed(request)

        // Almacenar las cookies de la respuesta para futuras solicitudes
        val receivedCookies = response.headers("Set-Cookie")
        if (receivedCookies.isNotEmpty()) {
            setCookies(receivedCookies)
        }

        return response
    }
}