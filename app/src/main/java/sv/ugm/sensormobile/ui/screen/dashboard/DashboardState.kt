package sv.ugm.sensormobile.ui.screen.dashboard

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import sv.ugm.sensormobile.ui.model.ChartSeriesUi
import sv.ugm.sensormobile.ui.util.DashboardNavDrawerItem

data class DashboardState(
    val isLoggedIn: Boolean? = null,
    
    @Stable val navDrawerItemList: List<DashboardNavDrawerItem> = listOf(
        DashboardNavDrawerItem.LightChart,
        DashboardNavDrawerItem.SoilMoistureChart,
        DashboardNavDrawerItem.AirQualityChart,
        DashboardNavDrawerItem.RaindropChart,
        DashboardNavDrawerItem.HumidityChart,
        DashboardNavDrawerItem.Temperature1Chart,
        DashboardNavDrawerItem.Temperature2Chart,
        DashboardNavDrawerItem.PressureChart,
        DashboardNavDrawerItem.ApproxAltitudeChart,
    ),
    
    @StringRes val chartTitle: Int = navDrawerItemList.first().title,
    
    val chartData: ChartSeriesUi = ChartSeriesUi(
        emptyList(),
        emptyList(),
        emptyList(),
    ),
    val isLoading: Boolean = false,
    val failureMessage: State<String?> = mutableStateOf(null),
)