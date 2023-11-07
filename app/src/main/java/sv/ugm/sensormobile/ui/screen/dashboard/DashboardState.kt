package sv.ugm.sensormobile.ui.screen.dashboard

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import sv.ugm.sensormobile.ui.model.ChartDataUi
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
    
    @StringRes val graphTitle: Int = navDrawerItemList.first().title,
    
    val chartData: ChartDataUi = ChartDataUi(
        emptyList(),
        emptyList(),
        emptyList(),
    ),
    val isLoading: Boolean = false,
    val failureMessage: State<String?> = mutableStateOf(null),
)