package com.example.marsphotos.model

data class ALUMNO(
    var matricula : String,
    var contrasenia : String,
    var estatus : String,
    var acceso : String
)
data class CargaAcademicaItem(
    var semipresencial: String? = null,
    var observaciones: String? = null,
    var docente: String? = null,
    var clvOficial: String? = null,
    var sabado: String? = null,
    var viernes: String? = null,
    var jueves: String? = null,
    var miercoles: String? = null,
    var martes: String? = null,
    var lunes: String? = null,
    var estadoMateria: String? = null,
    var creditosMateria: Int? = null,
    var materia: String? = null,
    var grupo: String? = null
    // Agrega más campos según la estructura de tu respuesta XML
    // ...
)