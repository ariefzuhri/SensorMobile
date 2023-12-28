package sv.ugm.sensormobile.data.source.remote.api

import retrofit2.http.GET
import sv.ugm.sensormobile.BuildConfig
import sv.ugm.sensormobile.data.source.remote.dto.SensorOutputResponse

interface ApiService {
    
    @GET(BuildConfig.sensorOutputEndpoint)
    suspend fun getSensorOutput(): SensorOutputResponse
    
}