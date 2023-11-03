package sv.ugm.sensormobile.ui.mapper

import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.domain.util.toDateTimeString
import sv.ugm.sensormobile.ui.model.SensorRecord
import javax.inject.Inject
import javax.inject.Singleton
import sv.ugm.sensormobile.domain.model.SensorRecord as SensorRecordDomain

@Singleton
class SensorRecordDataMapper @Inject constructor() {
    
    fun mapDomainToUi(input: List<SensorRecordDomain>): List<SensorRecord> {
        return input.map {
            SensorRecord(
                value = it.value,
                timestampMillis = it.timestampMillis,
                date = it.timestampMillis.toDateTimeString(Constants.DateTimePatterns.Formatted.DATE),
                time = it.timestampMillis.toDateTimeString(Constants.DateTimePatterns.Formatted.TIME),
            )
        }
    }
    
    fun mapToChartEntry(input: List<SensorRecordDomain>): Map<Number, Number> {
        return input.associate {
            it.timestampMillis to it.value
        }
    }
    
}