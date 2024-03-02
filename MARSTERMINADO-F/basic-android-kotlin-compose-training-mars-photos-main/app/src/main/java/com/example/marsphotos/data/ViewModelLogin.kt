package com.example.marsphotos.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.marsphotos.MarsPhotosApplication
import com.example.marsphotos.model.ALUMNO
import com.example.marsphotos.ui.Nav.PantallasNav
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.reflect.AccessibleObject

class VIEWLOGIN(Acciones: REPO) : ViewModel() {
    var AL by mutableStateOf(ALUMNO("","","",""))

    val Ac =Acciones


    fun obtenerDatos(M : String, C : String)
    {
        viewModelScope.launch {
          AL = Ac.Login(M,C)
        }
    }



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