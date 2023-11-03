package sv.ugm.sensormobile.domain.util

fun <T : Any, R : Any> Result<T>.mapWhenSuccess(
    transform: (value: T) -> R,
): Result<R> {
    return when (this) {
        is Result.Loading -> Result.Loading(isLoading)
        is Result.Success -> Result.Success(transform(data))
        is Result.Failure -> Result.Failure(message)
    }
}