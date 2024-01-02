package sv.ugm.sensormobile.presentation.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.domain.util.SensorDataType
import sv.ugm.sensormobile.presentation.ui.designsystem.icon.SensorMobileIcons

sealed interface NavDrawerItem {
    
    @get:StringRes
    val title: Int
    
    @get:DrawableRes
    val icon: Int
    
}

sealed class ChartNavDrawerItem(
    @StringRes override val title: Int,
    @DrawableRes override val icon: Int,
    val sensorDataType: SensorDataType,
) : NavDrawerItem {
    
    data object AirQualityChart : ChartNavDrawerItem(
        title = R.string.menu_air_quality_chart,
        icon = SensorMobileIcons.AirQualitySensor,
        sensorDataType = SensorDataType.AirQuality,
    )
    
    data object ApproxAltitudeChart : ChartNavDrawerItem(
        title = R.string.menu_approx_altitude_chart,
        icon = SensorMobileIcons.ApproxAltitudeSensor,
        sensorDataType = SensorDataType.ApproxAltitude,
    )
    
    data object HumidityChart : ChartNavDrawerItem(
        title = R.string.menu_humidity_chart,
        icon = SensorMobileIcons.HumiditySensor,
        sensorDataType = SensorDataType.Humidity,
    )
    
    data object LightChart : ChartNavDrawerItem(
        title = R.string.menu_light_chart,
        icon = SensorMobileIcons.LightSensor,
        sensorDataType = SensorDataType.Light,
    )
    
    data object PressureChart : ChartNavDrawerItem(
        title = R.string.menu_pressure_chart,
        icon = SensorMobileIcons.PressureSensor,
        sensorDataType = SensorDataType.Pressure,
    )
    
    data object RaindropChart : ChartNavDrawerItem(
        title = R.string.menu_raindrop_chart,
        icon = SensorMobileIcons.RaindropSensor,
        sensorDataType = SensorDataType.Raindrop,
    )
    
    data object SoilMoistureChart : ChartNavDrawerItem(
        title = R.string.menu_soil_moisture_chart,
        icon = SensorMobileIcons.SoilMoistureSensor,
        sensorDataType = SensorDataType.SoilMoisture,
    )
    
    data object Temperature1Chart : ChartNavDrawerItem(
        title = R.string.menu_temperature_1_chart,
        icon = SensorMobileIcons.Temperature1Sensor,
        sensorDataType = SensorDataType.Temperature1,
    )
    
    data object Temperature2Chart : ChartNavDrawerItem(
        title = R.string.menu_temperature_2_chart,
        icon = SensorMobileIcons.Temperature2Sensor,
        sensorDataType = SensorDataType.Temperature2,
    )
    
}