package sv.ugm.sensormobile.presentation.util

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

sealed class ChartNavDrawerItem(
    override val title: Int,
    override val icon: ImageVector,
    val sensorDataType: SensorDataType,
) : NavDrawerItem {
    
    data object AirQualityChart : ChartNavDrawerItem(
        title = R.string.menu_air_quality_chart,
        icon = Icons.Rounded.Air,
        sensorDataType = SensorDataType.AirQuality,
    )
    
    data object ApproxAltitudeChart : ChartNavDrawerItem(
        title = R.string.menu_approx_altitude_chart,
        icon = Icons.Rounded.Landscape,
        sensorDataType = SensorDataType.ApproxAltitude,
    )
    
    data object HumidityChart : ChartNavDrawerItem(
        title = R.string.menu_humidity_chart,
        icon = Icons.Rounded.InvertColors,
        sensorDataType = SensorDataType.Humidity,
    )
    
    data object LightChart : ChartNavDrawerItem(
        title = R.string.menu_light_chart,
        icon = Icons.Rounded.LightMode,
        sensorDataType = SensorDataType.LightData,
    )
    
    data object PressureChart : ChartNavDrawerItem(
        title = R.string.menu_pressure_chart,
        icon = Icons.Rounded.Compress,
        sensorDataType = SensorDataType.PressureData,
    )
    
    data object RaindropChart : ChartNavDrawerItem(
        title = R.string.menu_raindrop_chart,
        icon = Icons.Rounded.WaterDrop,
        sensorDataType = SensorDataType.Raindrop,
    )
    
    data object SoilMoistureChart : ChartNavDrawerItem(
        title = R.string.menu_soil_moisture_chart,
        icon = Icons.Rounded.Eco,
        sensorDataType = SensorDataType.SoilMoistureData,
    )
    
    data object Temperature1Chart : ChartNavDrawerItem(
        title = R.string.menu_temperature_1_chart,
        icon = Icons.Rounded.Thermostat,
        sensorDataType = SensorDataType.Temperature1,
    )
    
    data object Temperature2Chart : ChartNavDrawerItem(
        title = R.string.menu_temperature_2_chart,
        icon = Icons.Rounded.Thermostat,
        sensorDataType = SensorDataType.Temperature2,
    )
    
}