package sv.ugm.sensormobile.data.repository

import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.data.mapper.SensorOutputDataMapper
import sv.ugm.sensormobile.data.source.remote.SensorOutputRemoteDataSource
import sv.ugm.sensormobile.data.source.remote.dto.SensorOutputSnapshot
import sv.ugm.sensormobile.data.util.RemoteResource
import sv.ugm.sensormobile.data.util.RemoteResult
import sv.ugm.sensormobile.domain.model.SensorOutput
import sv.ugm.sensormobile.domain.repository.ISensorOutputRepository
import sv.ugm.sensormobile.domain.util.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorOutputRepository @Inject constructor(
    private val remoteDataSource: SensorOutputRemoteDataSource,
    private val dataMapper: SensorOutputDataMapper,
) : ISensorOutputRepository {
    
    override suspend fun getSensorOutput(): Flow<Result<List<SensorOutput>>> {
        return object : RemoteResource<List<SensorOutputSnapshot>, List<SensorOutput>>() {
            override suspend fun createCall(): Flow<RemoteResult<List<SensorOutputSnapshot>>> {
                return remoteDataSource.getSensorOutput()
            }
            
            override suspend fun onFetchSuccess(data: List<SensorOutputSnapshot>): List<SensorOutput> {
                return dataMapper.mapDataToDomain(
                    input = data,
                )
            }
        }.asFlow()
    }
    
}