package sv.ugm.sensormobile.data.util

sealed class LocalResult<out T> {
    data class Success<out T>(val data: T) : LocalResult<T>()
    data class Failure(val exception: Exception) : LocalResult<Nothing>()
}