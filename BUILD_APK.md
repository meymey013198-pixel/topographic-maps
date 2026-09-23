# Build the APK

This project is ready for a GitHub Actions build.

1. Upload this project to a GitHub repository.
2. Push to `main`, or open Actions and run **Build Android APK** manually.
3. Download the `TopoExportPro-debug-apk` artifact.
4. On the Android phone, enable installation of the downloaded APK when prompted.
5. Install the APK.

The current project is a functional Android starter and contains a demonstration DXF exporter. The production GIS pipeline (live OSM extraction, DEM download, real contour generation, reprojection, offline tile packs and production DWG conversion) still needs to be implemented before it should be treated as a field/survey-grade application.
