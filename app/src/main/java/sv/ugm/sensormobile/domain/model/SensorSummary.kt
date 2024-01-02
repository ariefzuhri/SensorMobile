package sv.ugm.sensormobile.domain.model

data class SensorSummary(
    val latestAirQuality: Float,
    val latestApproxAltitude: Float,
    val latestHumidity: Float,
    val latestLight: Float,
    val latestPressure: Float,
    val latestRaindrop: Float,
    val latestSoilMoisture: Float,
    val latestTemperature1: Float,
    val latestTemperature2: Float,
    val latestTimestampMillis: Long,
)