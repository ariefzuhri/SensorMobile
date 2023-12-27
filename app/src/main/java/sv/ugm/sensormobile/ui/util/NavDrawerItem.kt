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
import sv.ugm.sensormobile.domain.util.SensorDataType

sealed interface NavDrawerItem {
    
    @get:StringRes
    val title: Int
    
    val icon: ImageVector
    
}

sealed class DashboardNavDrawerItem(
    override val title: Int,
    override val icon: ImageVector,
    val sensorDataType: SensorDataType,
) : NavDrawerItem {
    
    data object AirQualityChart : DashboardNavDrawerItem(
        title = R.string.menu_air_quality_chart_dashboard,
        icon = Icons.Rounded.Air,
        sensorDataType = SensorDataType.AirQuality,
    )
    
    data object ApproxAltitudeChart : DashboardNavDrawerItem(
        title = R.string.menu_approx_altitude_chart_dashboard,
        icon = Icons.Rounded.Landscape,
        sensorDataType = SensorDataType.ApproxAltitude,
    )
    
    data object HumidityChart : DashboardNavDrawerItem(
        title = R.string.menu_humidity_chart_dashboard,
        icon = Icons.Rounded.InvertColors,
        sensorDataType = SensorDataType.Humidity,
    )
    
    data object LightChart : DashboardNavDrawerItem(
        title = R.string.menu_light_chart_dashboard,
        icon = Icons.Rounded.LightMode,
        sensorDataType = SensorDataType.LightData,
    )
    
    data object PressureChart : DashboardNavDrawerItem(
        title = R.string.menu_pressure_chart_dashboard,
        icon = Icons.Rounded.Compress,
        sensorDataType = SensorDataType.PressureData,
    )
    
    data object RaindropChart : DashboardNavDrawerItem(
        title = R.string.menu_raindrop_chart_dashboard,
        icon = Icons.Rounded.WaterDrop,
        sensorDataType = SensorDataType.Raindrop,
    )
    
    data object SoilMoistureChart : DashboardNavDrawerItem(
        title = R.string.menu_soil_moisture_chart_dashboard,
        icon = Icons.Rounded.Eco,
        sensorDataType = SensorDataType.SoilMoistureData,
    )
    
    data object Temperature1Chart : DashboardNavDrawerItem(
        title = R.string.menu_temperature_1_chart_dashboard,
        icon = Icons.Rounded.Thermostat,
        sensorDataType = SensorDataType.Temperature1,
    )
    
    data object Temperature2Chart : DashboardNavDrawerItem(
        title = R.string.menu_temperature_2_chart_dashboard,
        icon = Icons.Rounded.Thermostat,
        sensorDataType = SensorDataType.Temperature2,
    )
    
}