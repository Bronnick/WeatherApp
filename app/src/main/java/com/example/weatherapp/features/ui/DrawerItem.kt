package com.example.weatherapp.features.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun DrawerItem(
    imageVector: ImageVector,
    text: String,
    switchState: Boolean? = null,
    onCheckedChange: ((Boolean) -> Unit)? = null,
){
    Row(
        modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .fillMaxWidth()
            .drawBehind {
                val borderSize = 1.dp
                drawLine(
                    color = Color.White,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = borderSize.toPx()
                )
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )

        Text(
            text = text
        )

        if(switchState != null) {
            Switch(
                checked = switchState,
                onCheckedChange = onCheckedChange
            )
        }
    }
}
