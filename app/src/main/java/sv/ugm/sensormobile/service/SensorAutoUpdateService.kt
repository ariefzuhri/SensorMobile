package sv.ugm.sensormobile.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.domain.usecase.PullSensorOutputsUseCase
import sv.ugm.sensormobile.ui.util.buildNotification
import java.util.UUID
import javax.inject.Inject

@AndroidEntryPoint
class SensorAutoUpdateService : Service() {
    
    private val notificationId = UUID.randomUUID().hashCode()
    
    @Inject
    lateinit var pullSensorOutputsUseCase: PullSensorOutputsUseCase
    
    override fun onCreate() {
        super.onCreate()
        startService()
        pullSensorOutputs()
    }
    
    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int,
    ): Int {
        return START_STICKY
    }
    
    private fun startService() {
        startForeground(
            notificationId,
            buildNotification(
                context = this,
                channelId = getString(R.string.notif_channel_id_sensorautoupdate),
                id = notificationId,
                message = getString(R.string.notif_message_sensorautoupdate),
            ),
        )
    }
    
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)
    
    private fun pullSensorOutputs() {
        scope.launch {
            pullSensorOutputsUseCase().collect()
        }
    }
    
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    
    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
    
}