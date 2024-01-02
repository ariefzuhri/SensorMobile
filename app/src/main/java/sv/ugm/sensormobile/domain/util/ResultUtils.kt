package sv.ugm.sensormobile.domain.util

suspend fun <T, R> Result<T>.mapWhenSuccess(
    transform: suspend (value: T) -> R,
): Result<R> {
    return when (this) {
        is Result.Loading -> Result.Loading(isLoading)
        is Result.Success -> Result.Success(transform(data))
        is Result.Empty -> Result.Empty
        is Result.Failure -> Result.Failure(message)
    }
}