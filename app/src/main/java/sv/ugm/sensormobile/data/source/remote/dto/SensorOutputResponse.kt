package sv.ugm.sensormobile.data.source.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SensorOutputResponse(
    
    @Json(name = "data")
    val data: List<DataItem?>? = null,
    
    ) {
    
    @JsonClass(generateAdapter = true)
    data class DataItem(
        
        @Json(name = "ApproxAltitude")
        val approxAltitude: String? = null,
        
        @Json(name = "TimeAdded")
        val timeAdded: String? = null,
        
        @Json(name = "Temperature")
        val temperature: String? = null,
        
        @Json(name = "AirQuality")
        val airQuality: String? = null,
        
        @Json(name = "T")
        val t: String? = null,
        
        @Json(name = "Light")
        val light: String? = null,
        
        @Json(name = "H")
        val h: String? = null,
        
        @Json(name = "RainDrop")
        val rainDrop: String? = null,
        
        @Json(name = "PersentaseKelembapanTanah")
        val persentaseKelembapanTanah: String? = null,
        
        @Json(name = "Pressure")
        val pressure: String? = null,
        
        )
    
}