package sv.ugm.sensormobile.domain.util

sealed class SensorType{
    data object AirQualitySensor : SensorType()
    data object Altimeter : SensorType()
    data object Hygrometer : SensorType()
    data object Photodetector : SensorType()
    data object PressureSensor : SensorType()
    data object RainGauge : SensorType()
    data object SoilMoistureSensor : SensorType()
    data object Thermometer : SensorType()
}