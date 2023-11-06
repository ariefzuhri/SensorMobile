package sv.ugm.sensormobile.ui.screen.dashboard

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import sv.ugm.sensormobile.domain.util.SensorType
import sv.ugm.sensormobile.ui.model.ChartDataset
import sv.ugm.sensormobile.ui.util.DashboardNavDrawerItem

data class DashboardState(
    val isLoggedIn: Boolean? = null,
    
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
    
    val selectedSensorType: SensorType = navDrawerItemList.first().sensorType,
    @StringRes val graphTitle: Int = navDrawerItemList.first().title,
    
    val chartDataset: ChartDataset = ChartDataset(
        graphTitle,
        emptyList(),
    ),
    val isLoading: Boolean = false,
    val failureMessage: String? = null,
)