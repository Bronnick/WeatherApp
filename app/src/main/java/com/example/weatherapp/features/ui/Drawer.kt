package com.example.weatherapp.features.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import com.example.weatherapp.features.data.TemperatureState
import okhttp3.internal.immutableListOf

@Composable
fun Drawer(
    temperatureState: TemperatureState,
    onTemperatureSwitch: (Boolean) -> Unit
) {
    var drawerList = immutableListOf(
        DrawerItem(
            imageVector = Icons.Filled.Home,
            text = stringResource(id = R.string.drawer_item1),
            switchable = false
        ),
        DrawerItem(
            imageVector = Icons.Filled.Star,
            text = stringResource(id = R.string.drawer_item2),
            switchable = true,
            switchState =
                if(temperatureState == TemperatureState.CELSIUS) false else true,
            onCheckedChange = onTemperatureSwitch
        )
    )
}