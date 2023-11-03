package sv.ugm.sensormobile.ui.model

data class SensorRecord(
    val value: Float,
    val timestampMillis: Long,
    val date: String,
    val time: String,
)