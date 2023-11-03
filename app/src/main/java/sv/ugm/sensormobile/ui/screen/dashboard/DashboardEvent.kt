package sv.ugm.sensormobile.ui.screen.dashboard

import sv.ugm.sensormobile.domain.util.SensorType

sealed class DashboardEvent {
    
    data class OnSensorTypeSelected(
        val sensorType: SensorType,
    ) : DashboardEvent()
    
}