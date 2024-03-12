package com.example.marsphotos.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.marsphotos.data.VIEWLOGIN
import com.example.marsphotos.model.CargaAcademicaItem
import java.lang.reflect.Modifier

@Composable
fun CargaAcademicaList(viewModel: VIEWLOGIN = viewModel(factory = VIEWLOGIN.Factory),
                       navController: NavController
) {
    LazyColumn {
        items(viewModel.listaCarga?.size ?: 0) { index -> // Handle null list
            val item = viewModel.listaCarga?.get(index) // Safe access
            Log.d("ITEM CARGA",item.toString())
            if (item != null) {
                Column(
                 modifier = androidx.compose.ui.Modifier.fillMaxWidth()
                     .padding(16.dp)
                     .background(MaterialTheme.colorScheme.surface)
                ) {
                    Text(
                        text = "Materia: ${item.Materia}", // Assuming `nombre` is a property
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Grupo: ${item.Grupo}", // Assuming `grupo` is a property
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Docente: ${item.Docente}", // Assuming `grupo` is a property
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Lunes: ${item.Lunes}", // Assuming `grupo` is a property
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Martes: ${item.Martes}", // Assuming `grupo` is a property
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Miercoles: ${item.Miercoles}", // Assuming `grupo` is a property
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Jueves: ${item.Jueves}", // Assuming `grupo` is a property
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Viernes: ${item.Viernes}", // Assuming `grupo` is a property
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}