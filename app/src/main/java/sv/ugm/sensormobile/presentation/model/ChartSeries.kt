package sv.ugm.sensormobile.presentation.model

import androidx.annotation.StringRes

// Null labels are used to indicate that the label is equal to the value

data class ChartSeries(
    val datasets: List<ChartDataset>,
    val xAxisTitle: String?,
    val xAxisLabels: List<String?>,
    val yAxisTitle: String?,
    val yAxisLabels: List<String?>,
) {
    
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
    
}