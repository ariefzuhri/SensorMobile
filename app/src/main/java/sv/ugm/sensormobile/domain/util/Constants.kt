package sv.ugm.sensormobile.domain.util

object Constants {
    
    object DateTimePatterns {
        
        object Raw {
            
            const val TIMESTAMPS = "yyyy-MM-dd HH:mm:ss"
            
        }
        
        object Formatted {
            
            const val DATE = "dd/MM/yyyy"
            const val TIME = "HH:mm:ss"
            const val SHORT_DATE = "dd/MM/yy"
            const val SHORT_TIME = "HH:mm"
            
        }
        
    }
    
    const val SENSOR_AUTO_UPDATE_INTERVAL_MINUTES: Double = 5.0
    
}