package sv.ugm.sensormobile.domain.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

suspend fun tickerFlow(
    periodMinutes: Double,
    initialDelayMinutes: Double = 0.0,
): Flow<Unit> {
    delay((initialDelayMinutes * 60000).toLong())
    return flow {
        while (true) {
            emit(Unit)
            delay((periodMinutes * 60000).toLong())
        }
    }
}