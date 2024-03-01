package com.example.marsphotos.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
)
{
    var Ncontrol by remember { mutableStateOf("S20120185") }
    var Contraseña by remember { mutableStateOf("P%o48D_") }
    Column {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
        Text(text = "USUARIO")
        TextField(value = Ncontrol, onValueChange = {
            Ncontrol= it
        })
        Text(text = "CONTRASEÑA")
        TextField(value = Contraseña, onValueChange = {
            Contraseña=it
        })
        val scope = rememberCoroutineScope()
        Button(
            onClick = {
                scope.launch{
                    Log.d("BOTON", "ENTRO!")
                    viewModel.Ac.Login(Ncontrol,Contraseña)
                    navController.navigate(PantallasNav.SESION.route)
                }
                      },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                //.padding(_padding)
        ) {
            Text("Ingresar")
        }
    }
}

