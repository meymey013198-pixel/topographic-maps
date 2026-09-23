package com.topoexport.pro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.topoexport.pro.dxf.DxfWriter
import com.topoexport.pro.ui.MapPanel
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                TopoExportScreen(
                    onExport = { layers, interval ->
                        val out = File(getExternalFilesDir(null), "TopoExport_${System.currentTimeMillis()}.dxf")
                        DxfWriter().writeDemo(out, layers, interval)
                        out
                    }
                )
            }
        }
    }
}

@Composable
fun TopoExportScreen(onExport: (Set<String>, Double) -> File) {
    val layerNames = listOf("Terrain / DEM", "Contours", "Trees", "Roads", "Rivers", "Railways", "Buildings", "Boundaries", "Places")
    val enabled = remember { mutableStateMapOf<String, Boolean>().apply { layerNames.forEach { put(it, it in setOf("Terrain / DEM","Contours","Roads","Rivers","Railways")) } } }
    var interval by remember { mutableStateOf("5") }
    var message by remember { mutableStateOf("Select an area on the map, choose layers, then export.") }

    Column(Modifier.fillMaxSize()) {
        Text("TopoExport Pro", style = MaterialTheme.typography.headlineSmall, modifier = Modifier.padding(16.dp))
        MapPanel(Modifier.fillMaxWidth().weight(1f))
        LazyColumn(Modifier.heightIn(max = 290.dp).padding(horizontal = 12.dp)) {
            item { Text("Export layers", style = MaterialTheme.typography.titleMedium) }
            layerNames.forEach { name ->
                item {
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(name, modifier = Modifier.padding(vertical = 6.dp))
                        Switch(checked = enabled[name] == true, onCheckedChange = { enabled[name] = it })
                    }
                }
            }
            item {
                OutlinedTextField(value = interval, onValueChange = { interval = it }, label = { Text("Contour interval (m)") }, modifier = Modifier.fillMaxWidth())
                Spacer(Modifier.height(8.dp))
                Button(onClick = {
                    val f = onExport(enabled.filterValues { it }.keys, interval.toDoubleOrNull() ?: 5.0)
                    message = "DXF saved: ${f.absolutePath}"
                }, modifier = Modifier.fillMaxWidth()) { Text("EXPORT DXF") }
                Text(message, style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(vertical = 8.dp))
            }
        }
    }
}
