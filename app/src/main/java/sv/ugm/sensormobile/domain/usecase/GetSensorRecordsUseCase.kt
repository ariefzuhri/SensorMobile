package sv.ugm.sensormobile.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import sv.ugm.sensormobile.domain.model.SensorRecord
import sv.ugm.sensormobile.domain.repository.ISensorRecordRepository
import sv.ugm.sensormobile.domain.util.Result
import sv.ugm.sensormobile.domain.util.SensorType
import sv.ugm.sensormobile.domain.util.mapWhenSuccess
import javax.inject.Inject

class GetSensorRecordsUseCase @Inject constructor(
    private val sensorRecordRepository: ISensorRecordRepository,
) {
    
    suspend operator fun invoke(
        sensorType: SensorType,
    ): Flow<Result<List<SensorRecord>>> {
        return when (sensorType) {
            SensorType.AirQualitySensor -> sensorRecordRepository.getAirQualityRecords()
            SensorType.Altimeter -> sensorRecordRepository.getApproxAltitudeRecords()
            SensorType.Hygrometer -> sensorRecordRepository.getAirHumidityRecords()
            SensorType.Photodetector -> sensorRecordRepository.getPhotodetectorRecords()
            SensorType.PressureSensor -> sensorRecordRepository.getAirPressureRecords()
            SensorType.RainGauge -> sensorRecordRepository.getRainfallRecords()
            SensorType.SoilMoistureSensor -> sensorRecordRepository.getSoilMoistureRecords()
            SensorType.Thermometer -> sensorRecordRepository.getTemperatureRecords()
        }.map { result ->
            result.mapWhenSuccess { sensorRecords ->
                sensorRecords.sortedBy { it.timestampMillis }
            }
        }
    }
    
}