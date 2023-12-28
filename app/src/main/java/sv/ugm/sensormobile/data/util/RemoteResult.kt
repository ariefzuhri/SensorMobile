package sv.ugm.sensormobile.data.util

sealed class RemoteResult<out T> {
    data object Loading : RemoteResult<Nothing>()
    data class Success<out T>(val data: T) : RemoteResult<T>()
    data class Failure(val exception: Exception) : RemoteResult<Nothing>()
}