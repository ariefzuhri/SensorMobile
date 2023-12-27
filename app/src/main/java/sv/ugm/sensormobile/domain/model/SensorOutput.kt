package sv.ugm.sensormobile.domain.model

data class SensorOutput(
    val airQuality: Float,
    val approxAltitude: Float,
    val humidity: Float,
    val light: Float,
    val pressure: Float,
    val raindrop: Float,
    val soilMoisture: Float,
    val temperature1: Float,
    val temperature2: Float,
    val timestampMillis: Long,
)