package sv.ugm.sensormobile.data.util

sealed class LocalResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : LocalResult<T>()
    data class Failure(val exception: Exception) : LocalResult<Nothing>()
}