package sv.ugm.sensormobile.ui.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Air
import androidx.compose.material.icons.rounded.Compress
import androidx.compose.material.icons.rounded.Eco
import androidx.compose.material.icons.rounded.InvertColors
import androidx.compose.material.icons.rounded.Landscape
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Thermostat
import androidx.compose.material.icons.rounded.WaterDrop
import androidx.compose.ui.graphics.vector.ImageVector
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.domain.util.SensorType

sealed interface NavDrawerItem {
    
    @get:StringRes
    val title: Int
    
    val icon: ImageVector
    
}

sealed class DashboardNavDrawerItem(
    override val title: Int,
    override val icon: ImageVector,
    val sensorType: SensorType,
) : NavDrawerItem {
    
    data object AirHumidityGraph : DashboardNavDrawerItem(
        title = R.string.menu_air_humidity_graph_dashboard,
        icon = Icons.Rounded.InvertColors,
        sensorType = SensorType.Hygrometer,
    )
    
    data object AirQualityGraph : DashboardNavDrawerItem(
        title = R.string.menu_air_quality_graph_dashboard,
        icon = Icons.Rounded.Air,
        sensorType = SensorType.AirQualitySensor,
    )
    
    data object AltitudeGraph : DashboardNavDrawerItem(
        title = R.string.menu_altitude_graph_dashboard,
        icon = Icons.Rounded.Landscape,
        sensorType = SensorType.Altimeter,
    )
    
    data object PhotodetectorGraph : DashboardNavDrawerItem(
        title = R.string.menu_photodetector_graph_dashboard,
        icon = Icons.Rounded.LightMode,
        sensorType = SensorType.Photodetector,
    )
    
    data object PressureGraph : DashboardNavDrawerItem(
        title = R.string.menu_pressure_graph_dashboard,
        icon = Icons.Rounded.Compress,
        sensorType = SensorType.PressureSensor,
    )
    
    data object RainfallGraph : DashboardNavDrawerItem(
        title = R.string.menu_rainfall_graph_dashboard,
        icon = Icons.Rounded.WaterDrop,
        sensorType = SensorType.RainGauge,
    )
    
    data object SoilMoistureGraph : DashboardNavDrawerItem(
        title = R.string.menu_soil_moisture_graph_dashboard,
        icon = Icons.Rounded.Eco,
        sensorType = SensorType.SoilMoistureSensor,
    )
    
    data object TemperatureGraph : DashboardNavDrawerItem(
        title = R.string.menu_temperature_graph_dashboard,
        icon = Icons.Rounded.Thermostat,
        sensorType = SensorType.Thermometer,
    )
    
}