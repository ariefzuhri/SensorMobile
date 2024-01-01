package sv.ugm.sensormobile.presentation.util

import android.content.Context
import android.content.Intent
import android.os.Build

fun startService(context: Context?, intent: Intent) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        context?.startForegroundService(intent)
    } else {
        context?.startService(intent)
    }
}