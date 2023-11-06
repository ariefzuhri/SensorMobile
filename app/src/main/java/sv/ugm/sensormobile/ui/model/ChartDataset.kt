package sv.ugm.sensormobile.ui.model

import androidx.annotation.StringRes

data class ChartDataset(
    @StringRes val label: Int,
    val entries: List<ChartEntry>,
)