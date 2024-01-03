package sv.ugm.sensormobile.data.source.remote

import kotlinx.coroutines.flow.Flow
import sv.ugm.sensormobile.data.source.remote.api.ApiService
import sv.ugm.sensormobile.data.source.remote.api.response.SensorOutputsResponse
import sv.ugm.sensormobile.data.util.RemoteResult
import sv.ugm.sensormobile.data.util.invokeApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SensorOutputRemoteDataSource @Inject constructor(
    private val apiService: ApiService,
) {
    
    suspend fun getSensorOutputs(): Flow<RemoteResult<SensorOutputsResponse>> {
        return apiService.getSensorOutputs().invokeApi()
    }
    
}