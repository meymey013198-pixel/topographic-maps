# TopoExport Pro

Android starter architecture for an online/offline topographic export app.

## Planned production layers
- Terrain / DEM
- Automatic contours with user-selected interval
- Roads and paths
- Rivers / waterways
- Railways
- Buildings
- Trees / vegetation
- Boundaries and place labels
- Polygon/rectangle/circle area selection
- WGS84, UTM and Philippine PRS92 coordinate export
- DXF export with real projected coordinates
- Optional DWG conversion through a server-side CAD conversion service
- Offline cache for basemaps and downloaded vector/DEM tiles

## Data strategy
OpenStreetMap supplies roads, waterways, railways, buildings, trees and other mapped features. OSM data is ODbL and requires attribution.
For elevation, use a regional DEM provider (for the Philippines, configure a suitable public or licensed DEM source). Do not assume USGS 3DEP coverage outside its supported areas.

## Important
The included DXF writer and map are a functional starter. Replace the demo geometry with the selected map polygon, OSM vector query, DEM download, reprojection and contour-generation pipeline for a production build.
