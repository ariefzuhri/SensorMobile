package sv.ugm.sensormobile.ui.mapper

import androidx.annotation.StringRes
import sv.ugm.sensormobile.domain.model.SensorRecord
import sv.ugm.sensormobile.ui.model.ChartDataset
import sv.ugm.sensormobile.ui.model.ChartEntry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorRecordDataMapper @Inject constructor() {
    
    fun mapToChartDataset(
        input: List<SensorRecord>,
        @StringRes label: Int,
    ): ChartDataset {
        val chartEntries = input.map {
            ChartEntry(
                x = it.timestampMillis.toFloat(),
                y = it.value,
            )
        }
        return ChartDataset(
            label = label,
            entries = chartEntries,
        )
    }
    
}