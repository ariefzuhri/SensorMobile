package sv.ugm.sensormobile.presentation.ui.screen.chart

import androidx.annotation.StringRes
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import sv.ugm.sensormobile.presentation.model.ChartSeries
import sv.ugm.sensormobile.presentation.util.ChartNavDrawerItem

data class ChartState(
    val isLoggedIn: Boolean? = null,
    
    @Stable val navDrawerItemList: List<ChartNavDrawerItem> = listOf(
        ChartNavDrawerItem.LightChart,
        ChartNavDrawerItem.SoilMoistureChart,
        ChartNavDrawerItem.AirQualityChart,
        ChartNavDrawerItem.RaindropChart,
        ChartNavDrawerItem.HumidityChart,
        ChartNavDrawerItem.Temperature1Chart,
        ChartNavDrawerItem.Temperature2Chart,
        ChartNavDrawerItem.PressureChart,
        ChartNavDrawerItem.ApproxAltitudeChart,
    ),
    
    @StringRes val chartTitle: Int = navDrawerItemList.first().title,
    
    val chartData: ChartSeries = ChartSeries(
        emptyList(),
        emptyList(),
        emptyList(),
    ),
    val isLoading: Boolean = false,
    val failureMessage: State<String?> = mutableStateOf(null),
)