package sv.ugm.sensormobile.domain.repository

import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.domain.model.SensorRecord
import sv.ugm.sensormobile.domain.util.Result

interface ISensorRecordRepository {
    
    suspend fun getAirHumidityRecords(): Flow<Result<List<SensorRecord>>>
    
    suspend fun getAirQualityRecords(): Flow<Result<List<SensorRecord>>>
    
    suspend fun getAirPressureRecords(): Flow<Result<List<SensorRecord>>>
    
    suspend fun getApproxAltitudeRecords(): Flow<Result<List<SensorRecord>>>
    
    suspend fun getPhotodetectorRecords(): Flow<Result<List<SensorRecord>>>
    
    suspend fun getRainfallRecords(): Flow<Result<List<SensorRecord>>>
    
    suspend fun getSoilMoistureRecords(): Flow<Result<List<SensorRecord>>>
    
    suspend fun getTemperatureRecords(): Flow<Result<List<SensorRecord>>>
    
}