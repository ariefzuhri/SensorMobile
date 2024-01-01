package sv.ugm.sensormobile.data.source.remote.api

import retrofit2.http.GET
import sv.ugm.sensormobile.BuildConfig
import sv.ugm.sensormobile.data.source.remote.api.response.SensorOutputsResponse

interface ApiService {
    
    @GET(BuildConfig.sensorOutputsEndpoint)
    suspend fun getSensorOutputs(): SensorOutputsResponse
    
}