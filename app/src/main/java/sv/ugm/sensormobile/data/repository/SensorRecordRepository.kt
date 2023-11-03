package sv.ugm.sensormobile.data.repository

import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.data.mapper.SensorRecordDataMapper
import sv.ugm.sensormobile.data.source.remote.SensorRecordRemoteDataSource
import sv.ugm.sensormobile.data.source.remote.dto.SensorRecordSnapshot
import sv.ugm.sensormobile.data.util.RemoteResource
import sv.ugm.sensormobile.data.util.RemoteResult
import sv.ugm.sensormobile.domain.model.SensorRecord
import sv.ugm.sensormobile.domain.repository.ISensorRecordRepository
import sv.ugm.sensormobile.domain.util.Result
import sv.ugm.sensormobile.domain.util.SensorType
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorRecordRepository @Inject constructor(
    private val remoteDataSource: SensorRecordRemoteDataSource,
    private val dataMapper: SensorRecordDataMapper,
) : ISensorRecordRepository {
    
    override suspend fun getAirHumidityRecords(): Flow<Result<List<SensorRecord>>> {
        return object : RemoteResource<List<SensorRecordSnapshot>, List<SensorRecord>>() {
            override suspend fun createCall(): Flow<RemoteResult<List<SensorRecordSnapshot>>> {
                return remoteDataSource.getSensorRecords()
            }
            
            override suspend fun onFetchSuccess(data: List<SensorRecordSnapshot>): List<SensorRecord> {
                return dataMapper.mapDataToDomain(
                    input = data,
                    sensorType = SensorType.Hygrometer,
                )
            }
        }.asFlow()
    }
    
    override suspend fun getAirPressureRecords(): Flow<Result<List<SensorRecord>>> {
        return object : RemoteResource<List<SensorRecordSnapshot>, List<SensorRecord>>() {
            override suspend fun createCall(): Flow<RemoteResult<List<SensorRecordSnapshot>>> {
                return remoteDataSource.getSensorRecords()
            }
            
            override suspend fun onFetchSuccess(data: List<SensorRecordSnapshot>): List<SensorRecord> {
                return dataMapper.mapDataToDomain(
                    input = data,
                    sensorType = SensorType.PressureSensor,
                )
            }
        }.asFlow()
    }
    
    override suspend fun getAirQualityRecords(): Flow<Result<List<SensorRecord>>> {
        return object : RemoteResource<List<SensorRecordSnapshot>, List<SensorRecord>>() {
            override suspend fun createCall(): Flow<RemoteResult<List<SensorRecordSnapshot>>> {
                return remoteDataSource.getSensorRecords()
            }
            
            override suspend fun onFetchSuccess(data: List<SensorRecordSnapshot>): List<SensorRecord> {
                return dataMapper.mapDataToDomain(
                    input = data,
                    sensorType = SensorType.AirQualitySensor,
                )
            }
        }.asFlow()
    }
    
    override suspend fun getApproxAltitudeRecords(): Flow<Result<List<SensorRecord>>> {
        return object : RemoteResource<List<SensorRecordSnapshot>, List<SensorRecord>>() {
            override suspend fun createCall(): Flow<RemoteResult<List<SensorRecordSnapshot>>> {
                return remoteDataSource.getSensorRecords()
            }
            
            override suspend fun onFetchSuccess(data: List<SensorRecordSnapshot>): List<SensorRecord> {
                return dataMapper.mapDataToDomain(
                    input = data,
                    sensorType = SensorType.Altimeter,
                )
            }
        }.asFlow()
    }
    
    override suspend fun getPhotodetectorRecords(): Flow<Result<List<SensorRecord>>> {
        return object : RemoteResource<List<SensorRecordSnapshot>, List<SensorRecord>>() {
            override suspend fun createCall(): Flow<RemoteResult<List<SensorRecordSnapshot>>> {
                return remoteDataSource.getSensorRecords()
            }
            
            override suspend fun onFetchSuccess(data: List<SensorRecordSnapshot>): List<SensorRecord> {
                return dataMapper.mapDataToDomain(
                    input = data,
                    sensorType = SensorType.Photodetector,
                )
            }
        }.asFlow()
    }
    
    override suspend fun getRainfallRecords(): Flow<Result<List<SensorRecord>>> {
        return object : RemoteResource<List<SensorRecordSnapshot>, List<SensorRecord>>() {
            override suspend fun createCall(): Flow<RemoteResult<List<SensorRecordSnapshot>>> {
                return remoteDataSource.getSensorRecords()
            }
            
            override suspend fun onFetchSuccess(data: List<SensorRecordSnapshot>): List<SensorRecord> {
                return dataMapper.mapDataToDomain(
                    input = data,
                    sensorType = SensorType.RainGauge,
                )
            }
        }.asFlow()
    }
    
    override suspend fun getSoilMoistureRecords(): Flow<Result<List<SensorRecord>>> {
        return object : RemoteResource<List<SensorRecordSnapshot>, List<SensorRecord>>() {
            override suspend fun createCall(): Flow<RemoteResult<List<SensorRecordSnapshot>>> {
                return remoteDataSource.getSensorRecords()
            }
            
            override suspend fun onFetchSuccess(data: List<SensorRecordSnapshot>): List<SensorRecord> {
                return dataMapper.mapDataToDomain(
                    input = data,
                    sensorType = SensorType.SoilMoistureSensor,
                )
            }
        }.asFlow()
    }
    
    override suspend fun getTemperatureRecords(): Flow<Result<List<SensorRecord>>> {
        return object : RemoteResource<List<SensorRecordSnapshot>, List<SensorRecord>>() {
            override suspend fun createCall(): Flow<RemoteResult<List<SensorRecordSnapshot>>> {
                return remoteDataSource.getSensorRecords()
            }
            
            override suspend fun onFetchSuccess(data: List<SensorRecordSnapshot>): List<SensorRecord> {
                return dataMapper.mapDataToDomain(
                    input = data,
                    sensorType = SensorType.Thermometer,
                )
            }
        }.asFlow()
    }
    
}