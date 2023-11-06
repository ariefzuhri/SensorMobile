package sv.ugm.sensormobile.ui.screen.dashboard

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import sv.ugm.sensormobile.domain.util.SensorType
import sv.ugm.sensormobile.ui.model.SensorRecordUi
import sv.ugm.sensormobile.ui.util.DashboardNavDrawerItem

data class DashboardState(
    val isLoggedIn: Boolean? = null,
    
    val selectedSensorType: SensorType = SensorType.Photodetector,
    @StringRes val selectedSensorRecordName: Int? = DashboardNavDrawerItem.PhotodetectorGraph.title,
    
    val sensorRecords: List<SensorRecordUi> = emptyList(),
    val chartEntry: Map<Number, Number> = emptyMap(),
    val isLoading: Boolean = false,
    val failureMessage: String? = null,
    
    val navDrawerItemList: List<DashboardNavDrawerItem> = listOf(
    @Stable val navDrawerItemList: List<DashboardNavDrawerItem> = listOf(
        DashboardNavDrawerItem.PhotodetectorGraph,
        DashboardNavDrawerItem.SoilMoistureGraph,
        DashboardNavDrawerItem.AirQualityGraph,
        DashboardNavDrawerItem.RainfallGraph,
        DashboardNavDrawerItem.AirHumidityGraph,
        DashboardNavDrawerItem.TemperatureGraph,
        DashboardNavDrawerItem.PressureGraph,
        DashboardNavDrawerItem.AltitudeGraph,
    ),
)