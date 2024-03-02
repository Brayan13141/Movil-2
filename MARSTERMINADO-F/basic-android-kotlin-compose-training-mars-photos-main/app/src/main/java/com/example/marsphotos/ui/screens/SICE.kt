package com.example.marsphotos.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.marsphotos.R
import com.example.marsphotos.data.REPO
import com.example.marsphotos.data.VIEWLOGIN
import com.example.marsphotos.ui.Nav.PantallasNav
import kotlinx.coroutines.launch
@Composable
fun PantallaInicio(
    viewModel: VIEWLOGIN = viewModel(factory = VIEWLOGIN.Factory),
    navController: NavController
) {
    var Ncontrol by remember { mutableStateOf("S20120185") }
    var Contraseña by remember { mutableStateOf("P%o48D_") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        // Imagen de carga
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )

        // Campo de usuario
        CampoTexto("USUARIO", Ncontrol) {
            Ncontrol = it
        }

        // Campo de contraseña
        CampoTexto("CONTRASEÑA", Contraseña) {
            Contraseña = it
        }

        // Botón de inicio de sesión
        val scope = rememberCoroutineScope()
        BotonIngresar {
            scope.launch {
                Log.d("BOTON", "ENTRO!")
                viewModel.obtenerDatos(Ncontrol,Contraseña)
                navController.navigate(PantallasNav.SESION.route)
            }
        }
    }
}

@Composable
private fun CampoTexto(label: String, value: String, onValueChange: (String) -> Unit) {
    Column {
        Text(text = label)
        TextField(value = value, onValueChange = onValueChange)
    }
}

@Composable
private fun BotonIngresar(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier//.padding(vertical = 16.dp)
    ) {
        Text("Ingresar")
    }
}
