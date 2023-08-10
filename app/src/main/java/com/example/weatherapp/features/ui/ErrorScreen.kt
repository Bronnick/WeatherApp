package com.example.weatherapp.features.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorScreen(
    errorIconId: Int,
    messageId: Int
){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(
                id = errorIconId
            ),
            contentDescription = null
        )

        Text(
            text = """Something went wrong.
                | ${stringResource(
                        id = messageId 
                    )
                }
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