package com.example.marsphotos.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.marsphotos.data.OBJ
import com.example.marsphotos.data.VIEWLOGIN

@Composable
fun PantallaCalificaciones(viewModel: VIEWLOGIN = viewModel(factory = VIEWLOGIN.Factory), modifier: Modifier = Modifier,
                           navController: NavController
) {
    LazyColumn {
        items(viewModel.listaCalificacion.size){index ->//viewModel.lista?.size ?: 0) { index -> // Handle null list
            val item = viewModel.listaCalificacion.get(index) // Safe access
            if (item != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Text(text = "Nombre: ${item.Materia}", style = MaterialTheme.typography.titleLarge)
                    if (item.C1 != null) {Text(text = "U1: ${if (item.C1 != null) item.C1 else ""}", style = MaterialTheme.typography.bodySmall) 
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                    if (item.C2 != null) {Text(text = "U2: ${if (item.C2 != null) item.C2 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                    if (item.C3 != null) {Text(text = "U3: ${if (item.C3 != null) item.C3 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                     if (item.C4 != null) {Text(text = "U4: ${if (item.C4 != null) item.C4 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                     if (item.C5 != null) {Text(text = "U5: ${if (item.C5 != null) item.C5 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                     if (item.C6 != null) {Text(text = "U6: ${if (item.C6 != null) item.C6 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                     if (item.C7 != null) {Text(text = "U7: ${if (item.C7 != null) item.C7 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                     if (item.C8 != null) {Text(text = "U8: ${if (item.C8 != null) item.C8 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                     if (item.C9 != null) {Text(text = "U9: ${if (item.C9 != null) item.C9 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                     if (item.C10 != null) {Text(text = "U10: ${if (item.C10 != null) item.C10 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                     if (item.C11 != null) {Text(text = "U11: ${if (item.C11 != null) item.C11 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                     if (item.C12 != null) {Text(text = "U12: ${if (item.C12 != null) item.C12 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                     if (item.C13 != null) {Text(text = "U13: ${if (item.C13 != null) item.C13 else ""}", style = MaterialTheme.typography.bodySmall)
                    Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))} else{}
                }
            }
        }
    }
}
