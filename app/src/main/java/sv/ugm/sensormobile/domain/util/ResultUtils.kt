package sv.ugm.sensormobile.domain.util

suspend fun <T : Any, R : Any> Result<T>.mapWhenSuccess(
    transform: suspend (value: T) -> R,
): Result<R> {
    return when (this) {
        is Result.Loading -> Result.Loading(isLoading)
        is Result.Success -> Result.Success(transform(data))
        is Result.Failure -> Result.Failure(message)
    }
}