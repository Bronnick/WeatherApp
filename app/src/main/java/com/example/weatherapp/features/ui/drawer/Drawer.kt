package com.example.weatherapp.features.ui

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.weatherapp.R
import com.example.weatherapp.features.data.BackgroundImageState
import com.example.weatherapp.features.data.TemperatureState
import androidx.compose.ui.Modifier

@Composable
fun Drawer(
    temperatureState: TemperatureState,
    backgroundImageState: BackgroundImageState,
    onTemperatureSwitch: (Boolean) -> Unit,
    onBackgroundImageSwitch: (Boolean) -> Unit
) {

    DrawerItem(
        imageVector = Icons.Filled.Star,
        text = stringResource(id = R.string.drawer_item2),
        switchState =
            if(temperatureState == TemperatureState.CELSIUS) false else true,
        onCheckedChange = onTemperatureSwitch
    )
    DrawerItem(
        imageVector = ImageVector.vectorResource(
            id = R.drawable.baseline_image_24
        ),
        text = stringResource(id = R.string.drawer_item3),
        switchState =
            if(backgroundImageState == BackgroundImageState.ENABLED) false else true,
        onCheckedChange = onBackgroundImageSwitch
    )

}