package sv.ugm.sensormobile.presentation

import android.app.Application
import android.app.Notification
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.presentation.util.createNotificationChannel

@HiltAndroidApp
class MainApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        createNotificationChannels()
    }
    
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(
                context = this,
                id = getString(R.string.notif_channel_id_sensormonitoring),
                name = getString(R.string.notif_channel_name_sensormonitoring),
                description = getString(R.string.notif_channel_desc_sensormonitoring),
                importance = NotificationManager.IMPORTANCE_NONE,
                lockscreenVisibility = Notification.VISIBILITY_SECRET,
                canBubble = false,
                canShowBadge = false,
            )
        }
    }
    
}