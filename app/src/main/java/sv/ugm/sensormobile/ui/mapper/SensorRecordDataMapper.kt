package sv.ugm.sensormobile.ui.mapper

import sv.ugm.sensormobile.domain.model.SensorRecord
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.toDateTimeString
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
    
    fun mapToChartEntry(input: List<SensorRecord>): Map<Number, Number> {
        return input.associate {
            it.timestampMillis to it.value
        }
    }
    
}