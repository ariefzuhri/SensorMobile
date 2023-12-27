package sv.ugm.sensormobile.ui.screen.dashboard

import sv.ugm.sensormobile.domain.util.SensorDataType

sealed class DashboardEvent {
    
    data class OnSensorDataTypeSelected(
        val sensorDataType: SensorDataType,
    ) : DashboardEvent()
    
}