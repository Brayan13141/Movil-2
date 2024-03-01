package com.example.marsphotos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.marsphotos.R
import com.example.marsphotos.data.VIEWLOGIN


@Composable
fun PantallaSesion(
    viewModel: VIEWLOGIN = viewModel(factory = VIEWLOGIN.Factory), modifier: Modifier = Modifier,
    navController: NavHostController
) {
    Column {
        Text(text = "-----------")
        Text("----------------------", modifier = modifier
            .size(20.dp)
            .background(Color.Black))
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.loading)
        )
    }



}