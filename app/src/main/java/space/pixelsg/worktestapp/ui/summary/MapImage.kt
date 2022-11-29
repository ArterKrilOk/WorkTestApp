package space.pixelsg.worktestapp.ui.summary

import kotlin.math.*

object MapImage {
    //Magic function that converts lat/lon coordinates to x, y on map with specific zoom
    //https://gis.stackexchange.com/questions/133205/wmts-convert-geolocation-lat-long-to-tile-index-at-a-given-zoom-level

    private fun magicFunction(lat: Double, lon: Double, zoom: Int): Pair<Int, Int> {
        val latRad = Math.toRadians(lat)
        val n = 2.toDouble().pow(zoom)
        val xTile = ((lon + 180.0) / 360.0 * n).toInt()
        val yTile = ((1.0 - ln(tan(latRad) + (1 / cos(latRad))) / PI) / 2.0 * n).toInt()
        return xTile to yTile
    }

    fun mapImage(lat: Double, lon: Double, zoom: Int = 12): String {
        val coords = magicFunction(lat, lon, zoom)
        return "https://server.arcgisonline.com/ArcGIS/rest/services/World_Topo_Map/MapServer/tile/$zoom/${coords.second}/${coords.first}"
    }
}