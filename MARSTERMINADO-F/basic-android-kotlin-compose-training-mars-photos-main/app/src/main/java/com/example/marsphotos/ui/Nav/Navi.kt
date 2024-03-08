package com.example.marsphotos.ui.Nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marsphotos.data.VIEWLOGIN
import com.example.marsphotos.data.ViewModelLocal
import com.example.marsphotos.model.ALUMNO
import com.example.marsphotos.model.Calificaciones
import com.example.marsphotos.model.CargaAcademicaItem
import com.example.marsphotos.ui.screens.CargaAcademicaList
import com.example.marsphotos.ui.screens.PantallaCalificaciones
import com.example.marsphotos.ui.screens.PantallaInicio
import com.example.marsphotos.ui.screens.PantallaSesion


@Composable
fun App(
    viewModel: VIEWLOGIN = viewModel(factory = VIEWLOGIN.Factory),
    viewModel2: ViewModelLocal = viewModel(factory = ViewModelLocal.Factory)
) {
    val navController = rememberNavController()
    val myViewModel: VIEWLOGIN = viewModel
    val myViewModel2: ViewModelLocal = viewModel2


    NavHost(
        navController = navController,
        startDestination = PantallasNav.LOGIN.route
    ) {
        composable(PantallasNav.LOGIN.route) {
            PantallaInicio(myViewModel,navController)
        }
        composable(PantallasNav.SESION.route) {
            PantallaSesion(myViewModel2,myViewModel, modifier = Modifier,navController)
            //PantallaSesion(alumno = ALUMNO)
        }
        composable(PantallasNav.CARGA.route) {
            CargaAcademicaList(myViewModel,navController)

        }
        composable(PantallasNav.CALI.route) {
            PantallaCalificaciones(myViewModel, modifier = Modifier,navController)

        }
    }
}
