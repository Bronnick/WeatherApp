package com.example.weatherapp.features.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import androidx.compose.runtime.*

@Composable
fun FocusedWeatherBar(
    onCancelButtonClick: () -> Unit
) {
    var textFieldValue by remember { mutableStateOf("") }

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
    ){
        TextField(
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
            }
        )
        IconButton(
            onClick = onCancelButtonClick,
            modifier = Modifier
                .fillMaxWidth()) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null
            )
        }
    }
}