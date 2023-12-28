package sv.ugm.sensormobile.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> T.invoke(): Flow<RemoteResult<T>> {
    return flow {
        try {
            emit(RemoteResult.Loading)
            val response = this@invoke
            emit(RemoteResult.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RemoteResult.Failure(e))
        }
    }
}