package sv.ugm.sensormobile.presentation.ui.screen.chart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import sv.ugm.sensormobile.domain.util.SensorDataType
import sv.ugm.sensormobile.presentation.model.ChartSeries

data class ChartState(
    val selectedSensorDataType: SensorDataType = SensorDataType.Light,
    val chartData: ChartSeries = ChartSeries(
        emptyList(),
        null,
        emptyList(),
        null,
        emptyList(),
    ),
    val isLoading: Boolean = false,
    val failureMessage: State<String?> = mutableStateOf(null),
)