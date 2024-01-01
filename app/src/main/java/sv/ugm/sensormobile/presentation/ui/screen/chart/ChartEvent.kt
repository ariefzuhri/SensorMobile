package sv.ugm.sensormobile.presentation.ui.screen.chart

import sv.ugm.sensormobile.domain.util.SensorDataType

sealed class ChartEvent {
    
    data class OnSensorDataTypeSelected(
        val sensorDataType: SensorDataType,
    ) : ChartEvent()
    
}