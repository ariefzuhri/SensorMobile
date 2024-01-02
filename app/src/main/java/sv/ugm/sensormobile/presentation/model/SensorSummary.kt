package sv.ugm.sensormobile.presentation.model

data class SensorSummary(
    val latestAirQuality: SensorData,
    val latestApproxAltitude: SensorData,
    val latestHumidity: SensorData,
    val latestLight: SensorData,
    val latestPressure: SensorData,
    val latestRaindrop: SensorData,
    val latestSoilMoisture: SensorData,
    val latestTemperature1: SensorData,
    val latestTemperature2: SensorData,
    val latestTimestampMillis: String?,
)