package sv.ugm.sensormobile.ui.util

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.ui.MainActivity
import sv.ugm.sensormobile.ui.designsystem.icon.SensorMobileIcons

fun Notification.show(
    context: Context,
    id: Int,
) {
    val notificationManager = ContextCompat.getSystemService(
        context,
        NotificationManager::class.java,
    ) as NotificationManager
    notificationManager.notify(
        id,
        this,
    )
}

fun buildNotification(
    context: Context,
    channelId: String,
    id: Int,
    title: String? = null,
    message: String? = null,
): Notification {
    val contentIntent = Intent(
        context,
        MainActivity::class.java,
    )
    val pendingIntent = PendingIntent.getActivity(
        context,
        id,
        contentIntent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT,
    )
    
    val builder = NotificationCompat.Builder(
        context,
        channelId,
    ).apply {
        setContentIntent(pendingIntent)
        
        setSmallIcon(SensorMobileIcons.Logo)
        color = context.getColor(R.color.purple_40)
        
        setAutoCancel(true)
        setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
        priority = NotificationCompat.PRIORITY_HIGH
        
        if (title?.isNotEmpty() == true) {
            setContentTitle(title)
        }
        if (message?.isNotEmpty() == true) {
            setContentText(message)
            setStyle(NotificationCompat.BigTextStyle().bigText(message))
        }
    }
    return builder.build()
}

@RequiresApi(Build.VERSION_CODES.O)
fun createNotificationChannel(
    context: Context,
    id: String,
    name: String,
    description: String? = null,
    importance: Int = NotificationManager.IMPORTANCE_HIGH,
    lockscreenVisibility: Int = Notification.VISIBILITY_PUBLIC,
    canBubble: Boolean = true,
    canShowBadge: Boolean = true,
) {
    val channel = NotificationChannel(
        id,
        name,
        importance,
    )
    
    channel.description = description
    
    // Should enable floating notification
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) channel.setAllowBubbles(canBubble)
    // Should show badge on app icon
    channel.setShowBadge(canShowBadge)
    // Should show notification on lock screen
    channel.lockscreenVisibility = lockscreenVisibility
    
    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(channel)
}