package com.example.marsphotos.model

data class ALUMNO(
    var matricula : String,
    var contrasenia : String,
    var estatus : String,
    var acceso : String
)
data class CargaAcademicaItem(
    val Semipresencial: String = "",
    val Observaciones: String = "",
    val Docente: String = "",
    val clvOficial: String = "",
    val Sabado: String = "",
    val Viernes: String = "",
    val Jueves: String = "",
    val Miercoles: String = "",
    val Martes: String = "",
    val Lunes: String = "",
    val EstadoMateria: String = "",
    val CreditosMateria: Int = 0,
    val Materia: String = "",
    val Grupo: String = ""

)