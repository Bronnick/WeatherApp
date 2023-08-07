package com.example.weatherapp.features.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R

@Composable
fun ErrorScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(
                id = R.drawable.baseline_wifi_off_24
            ),
            contentDescription = null
        )

        Text(
            text = """Something went wrong.
                | Check your internet connection
            """.trimMargin(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(
                    color = MaterialTheme.colors.background,
                    shape = CircleShape
                )
                .border(
                    border = BorderStroke(
                        width = 2.dp,
                        color = MaterialTheme.colors.error
                    ),
                    shape = CircleShape
                )
                .padding(10.dp)
        )
    }
}