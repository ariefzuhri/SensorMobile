package sv.ugm.sensormobile.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sv.ugm.sensormobile.domain.util.Result

abstract class LocalResource<DTOType : Any, DomainType : Any> {
    
    private var result: Flow<Result<DomainType>> = flow {
        emit(Result.Loading(true))
        createCall().collect { localResult ->
            when (localResult) {
                is LocalResult.Success -> {
                    val data = localResult.data
                    if (data is Collection<*> && data.isEmpty()) {
                        emit(Result.Empty)
                    } else {
                        emit(Result.Success(onFetchSuccess(data)))
                    }
                }
                
                is LocalResult.Failure -> {
                    emit(
                        Result.Failure(
                            localResult.exception.message
                                ?: localResult.exception.localizedMessage
                        )
                    )
                }
            }
        }
    }
    
    protected abstract suspend fun createCall(): Flow<LocalResult<DTOType>>
    
    protected abstract suspend fun onFetchSuccess(data: DTOType): DomainType
    
    fun asFlow(): Flow<Result<DomainType>> {
        return result
    }
    
}