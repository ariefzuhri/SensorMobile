package sv.ugm.sensormobile.domain.util

sealed class SensorDataType(
    val id: Int,
) {
    
    data object AirQuality : SensorDataType(1)
    data object ApproxAltitude : SensorDataType(2)
    data object Humidity : SensorDataType(3)
    data object Light : SensorDataType(4)
    data object Pressure : SensorDataType(5)
    data object Raindrop : SensorDataType(6)
    data object SoilMoisture : SensorDataType(7)
    data object Temperature1 : SensorDataType(8)
    data object Temperature2 : SensorDataType(9)
    
}