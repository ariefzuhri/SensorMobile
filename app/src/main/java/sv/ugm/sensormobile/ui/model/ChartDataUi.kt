package sv.ugm.sensormobile.ui.model

import androidx.annotation.StringRes

// Null labels are used to indicate that the label is equal to the value

data class ChartDataUi(
    val datasets: List<ChartDataset>,
    val xAxisLabelList: List<String?>,
    val yAxisLabelList: List<String?>,
)

data class ChartDataset(
    @StringRes val label: Int,
    val entries: List<ChartEntry>,
)

data class ChartEntry(
    val xValue: Float,
    val xMarkerLabel: String?,
    val yValue: Float,
    val yMarkerLabel: String?,
)