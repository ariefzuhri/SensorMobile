package sv.ugm.sensormobile.presentation.util

import android.content.Context
import android.content.Intent
import sv.ugm.sensormobile.presentation.service.SensorAutoUpdateService

fun startSensorAutoUpdateService(context: Context?) {
    val intent = Intent(
        context,
        SensorAutoUpdateService::class.java,
    )
    startService(context, intent)
}