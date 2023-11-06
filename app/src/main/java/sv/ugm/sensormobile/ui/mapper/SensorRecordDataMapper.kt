package sv.ugm.sensormobile.ui.mapper

import androidx.annotation.StringRes
import sv.ugm.sensormobile.domain.model.SensorRecord
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.toDateTimeString
import sv.ugm.sensormobile.ui.model.ChartDataset
import sv.ugm.sensormobile.ui.model.ChartEntry
import sv.ugm.sensormobile.ui.model.SensorRecordUi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorRecordDataMapper @Inject constructor() {
    
    fun mapDomainToUi(input: List<SensorRecord>): List<SensorRecordUi> {
        return input.map {
            SensorRecordUi(
                value = it.value,
                timestampMillis = it.timestampMillis,
                date = it.timestampMillis.toDateTimeString(Constants.DateTimePatterns.Formatted.DATE),
                time = it.timestampMillis.toDateTimeString(Constants.DateTimePatterns.Formatted.TIME),
            )
        }
    }
    
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