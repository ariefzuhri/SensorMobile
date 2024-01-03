package sv.ugm.sensormobile.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

fun <T> T.invokeApi(): Flow<RemoteResult<T>> {
    return flow {
        try {
            emit(RemoteResult.Loading)
            val response = this@invokeApi
            emit(RemoteResult.Success(response))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(RemoteResult.Failure(e))
        }
    }
}

fun <T> Flow<T>.invokeLocal(): Flow<LocalResult<T>> {
    return this.map {
        LocalResult.Success(it)
    }
}