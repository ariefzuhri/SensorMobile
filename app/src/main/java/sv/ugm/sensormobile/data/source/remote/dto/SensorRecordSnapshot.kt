package sv.ugm.sensormobile.data.source.remote.dto

import com.google.firebase.database.PropertyName

data class SensorRecordSnapshot(
    
    @PropertyName("AirQuality")
    val airQuality: String? = null,
    
    @PropertyName("ApproxAltitude")
    val approxAltitude: String? = null,
    
    @PropertyName("H")
    val h: String? = null,
    
    @PropertyName("Light")
    val light: String? = null,
    
    @PropertyName("PersentaseKelembapanTanah")
    val persentaseKelembapanTanah: String? = null,
    
    @PropertyName("Pressure")
    val pressure: String? = null,
    
    @PropertyName("RainDrop")
    val rainDrop: String? = null,
    
    @PropertyName("Temperature")
    val temperature: String? = null,
    
    @PropertyName("TimeStamp")
    val timestamp: String? = null,
    
    )