package sv.ugm.sensormobile.presentation.util

import android.content.Context
import android.content.Intent
import sv.ugm.sensormobile.presentation.service.SensorMonitoringService

fun startSensorMonitoringService(context: Context?) {
    val intent = Intent(
        context,
        SensorMonitoringService::class.java,
    )
    startService(context, intent)
}

fun stopSensorMonitoringService(context: Context?) {
    val intent = Intent(
        context,
        SensorMonitoringService::class.java,
    )
    context?.stopService(intent)
}