package sv.ugm.sensormobile.domain.util

sealed class Result<out T : Any> {
    data class Loading(val isLoading: Boolean) : Result<Nothing>()
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Failure(val message: String) : Result<Nothing>()
}