package sv.ugm.sensormobile.presentation.model

import sv.ugm.sensormobile.domain.util.SensorDataType

data class SensorData(
    val type: SensorDataType,
    val value: Float?,
    val unit: String,
)