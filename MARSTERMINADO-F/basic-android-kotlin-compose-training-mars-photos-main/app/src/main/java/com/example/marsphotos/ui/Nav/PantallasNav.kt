package com.example.marsphotos.ui.Nav


sealed class PantallasNav (val route: String){
    object LOGIN: PantallasNav("LOGIN")
    object SESION: PantallasNav("SESION")
}