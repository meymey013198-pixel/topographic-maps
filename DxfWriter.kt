package com.topoexport.pro.dxf

import java.io.File

class DxfWriter {
    fun writeDemo(file: File, layers: Set<String>, contourInterval: Double) {
        file.parentFile?.mkdirs()
        file.bufferedWriter().use { w ->
            fun p(a: String, b: String) { w.append(a).append('\n').append(b).append('\n') }
            p("0","SECTION"); p("2","HEADER")
            p("9","$ACADVER"); p("1","AC1027")
            p("0","ENDSEC")
            p("0","SECTION"); p("2","TABLES")
            p("0","TABLE"); p("2","LAYER"); p("70", layers.size.toString())
            layers.forEachIndexed { i, layer ->
                p("0","LAYER"); p("2", layer.uppercase().replace(" ","_"))
                p("70","0"); p("62", (i + 1).toString()); p("6","CONTINUOUS")
            }
            p("0","ENDTAB"); p("0","ENDSEC")
            p("0","SECTION"); p("2","ENTITIES")
            // Demonstration entities; production build replaces these with selected OSM/DEM geometry.
            if ("Contours" in layers) {
                for (i in 0..4) {
                    val z = i * contourInterval
                    p("0","POLYLINE"); p("8","CONTOURS"); p("66","1"); p("70","0")
                    listOf(0.0 to 0.0, 100.0 to 0.0, 100.0 to 100.0, 0.0 to 100.0).forEach { (x,y) ->
                        p("0","VERTEX"); p("8","CONTOURS"); p("10",x.toString()); p("20",y.toString()); p("30",z.toString())
                    }
                    p("0","SEQEND")
                }
            }
            p("0","ENDSEC"); p("0","EOF")
        }
    }
}
