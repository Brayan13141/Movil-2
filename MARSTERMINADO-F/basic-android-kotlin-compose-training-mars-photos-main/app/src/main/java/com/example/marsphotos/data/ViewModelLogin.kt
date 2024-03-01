package com.example.marsphotos.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.ui.Nav.PantallasNav
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class VIEWLOGIN(Acciones: REPO) : ViewModel() {
    var NControl by mutableStateOf("")
    var Contraseña by mutableStateOf("")
    val Ac =Acciones



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
          initializer {
                val app = (this[APPLICATION_KEY] as MarsPhotosApplication)
                val R = app.container.REP
                VIEWLOGIN(Acciones = R)
          }
        }
    }
}