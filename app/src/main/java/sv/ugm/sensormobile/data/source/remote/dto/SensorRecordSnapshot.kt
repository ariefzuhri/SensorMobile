package sv.ugm.sensormobile.data.source.remote.dto

import com.google.firebase.database.PropertyName

data class SensorRecordSnapshot(
    
    @PropertyName("AirQuality")
    val airQuality: Any? = null,
    
    @PropertyName("ApproxAltitude")
    val approxAltitude: Any? = null,
    
    @PropertyName("H")
    val h: Any? = null,
    
    @PropertyName("Light")
    val light: Any? = null,
    
    @PropertyName("PersentaseKelembapanTanah")
    val persentaseKelembapanTanah: Any? = null,
    
    @PropertyName("Pressure")
    val pressure: Any? = null,
    
    @PropertyName("RainDrop")
    val rainDrop: Any? = null,
    
    @PropertyName("Temperature")
    val temperature: Any? = null,
    
    @PropertyName("TimeStamp")
    val timestamp: Any? = null,
    
    )