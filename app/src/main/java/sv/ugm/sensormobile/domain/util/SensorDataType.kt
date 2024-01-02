package sv.ugm.sensormobile.domain.util

sealed class SensorDataType {
    data object AirQuality : SensorDataType()
    data object ApproxAltitude : SensorDataType()
    data object Humidity : SensorDataType()
    data object Light : SensorDataType()
    data object Pressure : SensorDataType()
    data object Raindrop : SensorDataType()
    data object SoilMoisture : SensorDataType()
    data object Temperature1 : SensorDataType()
    data object Temperature2 : SensorDataType()
}