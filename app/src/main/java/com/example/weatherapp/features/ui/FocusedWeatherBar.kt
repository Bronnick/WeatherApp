package com.example.weatherapp.features.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.text.input.ImeAction
import com.example.weatherapp.features.data.AutocompleteInfo

@Composable
fun FocusedWeatherBar(
    focusedTextState: String,
    autocompleteList: List<AutocompleteInfo>,
    onFocusedTextChange: (String) -> Unit,
    onCancelButtonClick: () -> Unit,
    onKeyboardSearchClick: (String) -> Unit
) {

    Column {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = focusedTextState,
                onValueChange = onFocusedTextChange,
                singleLine = true,
                trailingIcon = {
                    IconButton(
                        onClick = onCancelButtonClick,
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = null
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        onKeyboardSearchClick(focusedTextState)
                    }
                )
            )
        }
        for(item in autocompleteList){
            Text(
                text="${item.name}, ${item.region}, ${item.country}",
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 50.dp)
                    .drawBehind {
                        val borderSize = 1.dp
                        drawLine(
                            color = Color.White,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = borderSize.toPx()
                        )
                    }
                    .clickable {
                        item.name?.let{
                            onKeyboardSearchClick(it)
                        }
                    }
            )
        }
    }
}