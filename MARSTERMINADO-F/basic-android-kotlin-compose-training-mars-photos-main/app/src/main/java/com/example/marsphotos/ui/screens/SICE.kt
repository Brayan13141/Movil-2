package com.example.marsphotos.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PantallaInicio(
    viewModel: VIEWLOGIN = viewModel(factory = VIEWLOGIN.Factory),
    navController: NavController
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
       Spacer(modifier = Modifier.size(100.dp))
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.logo),
            contentDescription = "ITSUR"
        )
        TextField(value = viewModel.Ncontrol,
            onValueChange = {viewModel.fNcontrol(it)}
        )
        TextField(value = viewModel.Contraseña,
            onValueChange = {viewModel.fContraseña(it)}
        )
        var B by remember {
          mutableStateOf(true)
        }
        val Rutina = rememberCoroutineScope()
        BotonIngresar {
            Rutina.launch {
                B = viewModel.IniciarSesion(viewModel.Ncontrol,viewModel.Contraseña)
                if (B)
                {
                    navController.navigate(PantallasNav.SESION.route)
                }
            }
        }
        if (B==false) {
                B = SimpleErrorDialog()

        }
    }
}
@Composable
fun SimpleErrorDialog(): Boolean {
    var dialogClosed by remember { mutableStateOf(false) }

    if (!dialogClosed) {
        AlertDialog(
            onDismissRequest = {
                dialogClosed = true
            },
            title = { Text("Error") },
            text = { Text("Hubo un error al procesar la solicitud.") },
            confirmButton = {
                TextButton(onClick = {
                    dialogClosed = true
                }) {
                    Text("Aceptar")
                }
            }
        )
    }

    return dialogClosed
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
