package sv.ugm.sensormobile.data.repository

import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.data.mapper.SensorOutputDataMapper
import sv.ugm.sensormobile.data.source.local.SensorOutputLocalDataSource
import sv.ugm.sensormobile.data.source.local.provider.room.entity.SensorOutputEntity
import sv.ugm.sensormobile.data.source.remote.SensorOutputRemoteDataSource
import sv.ugm.sensormobile.data.source.remote.api.response.SensorOutputsResponse
import sv.ugm.sensormobile.data.util.LocalResource
import sv.ugm.sensormobile.data.util.LocalResult
import sv.ugm.sensormobile.data.util.RemoteResource
import sv.ugm.sensormobile.data.util.RemoteResult
import sv.ugm.sensormobile.domain.model.SensorOutput
import sv.ugm.sensormobile.domain.repository.ISensorOutputRepository
import sv.ugm.sensormobile.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorOutputRepository @Inject constructor(
    private val localDataSource: SensorOutputLocalDataSource,
    private val remoteDataSource: SensorOutputRemoteDataSource,
    private val dataMapper: SensorOutputDataMapper,
) : ISensorOutputRepository {
    
    override suspend fun getSensorOutputsFromLocal(): Flow<Result<List<SensorOutput>>> {
        return object : LocalResource<List<SensorOutputEntity>, List<SensorOutput>>() {
            override suspend fun createCall(): Flow<LocalResult<List<SensorOutputEntity>>> {
                return localDataSource.getSensorOutputs()
            }
            
            override suspend fun onFetchSuccess(data: List<SensorOutputEntity>): List<SensorOutput> {
                return dataMapper.mapDataToDomain(
                    input = data,
                )
            }
        }.asFlow()
    }
    
    override suspend fun getSensorOutputsFromRemote(): Flow<Result<List<SensorOutput>>> {
        return object : RemoteResource<SensorOutputsResponse, List<SensorOutput>>() {
            override suspend fun createCall(): Flow<RemoteResult<SensorOutputsResponse>> {
                return remoteDataSource.getSensorOutputs()
            }
            
            override suspend fun onFetchSuccess(data: SensorOutputsResponse): List<SensorOutput> {
                return dataMapper.mapDataToDomain(
                    input = data,
                )
            }
        }.asFlow()
    }
    
    override suspend fun replaceSensorOutputsLocal(sensorOutputs: List<SensorOutput>) {
        localDataSource.clearSensorOutputs()
        localDataSource.insertSensorOutputs(
            dataMapper.mapDomainToData(
                input = sensorOutputs,
            )
        )
    }
    
}