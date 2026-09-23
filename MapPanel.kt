package com.topoexport.pro.ui

import android.view.View
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import org.maplibre.android.maps.MapView
import org.maplibre.android.maps.Style

@Composable
fun MapPanel(modifier: Modifier = Modifier) {
    AndroidView(modifier = modifier, factory = { context ->
        MapView(context).apply {
            onCreate(null)
            getMapAsync { map ->
                map.setStyle(Style.Builder().fromUri("https://demotiles.maplibre.org/style.json"))
            }
        }
    })
}
