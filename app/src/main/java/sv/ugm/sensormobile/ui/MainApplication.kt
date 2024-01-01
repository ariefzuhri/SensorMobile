package sv.ugm.sensormobile.ui

import android.app.Application
import android.app.Notification
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.ui.util.createNotificationChannel
import sv.ugm.sensormobile.ui.util.startSensorAutoUpdateService

@HiltAndroidApp
class MainApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
        startSensorAutoUpdateService(this)
    }
    
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(
                context = this,
                id = getString(R.string.notif_channel_id_sensorautoupdate),
                name = getString(R.string.notif_channel_name_sensorautoupdate),
                description = getString(R.string.notif_channel_desc_sensorautoupdate),
                importance = NotificationManager.IMPORTANCE_NONE,
                lockscreenVisibility = Notification.VISIBILITY_SECRET,
                canBubble = false,
                canShowBadge = false,
            )
        }
    }
    
}