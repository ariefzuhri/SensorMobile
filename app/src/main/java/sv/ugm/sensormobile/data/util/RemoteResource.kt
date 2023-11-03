package sv.ugm.sensormobile.data.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import sv.ugm.sensormobile.domain.util.Result

abstract class RemoteResource<DTOType : Any, DomainType : Any> {
    
    private var result: Flow<Result<DomainType>> = flow {
        
        createCall().collect { remoteResult ->
            when (remoteResult) {
                is RemoteResult.Loading -> {
                    emit(Result.Loading(true))
                }
                
                is RemoteResult.Success -> {
                    val data = remoteResult.data
                    emit(Result.Success(onFetchSuccess(data)))
                }
                
                is RemoteResult.Failure -> {
                    emit(
                        Result.Failure(
                            remoteResult.exception.message
                                ?: remoteResult.exception.localizedMessage
                        )
                    )
                }
            }
        }
    }
    
    protected abstract suspend fun createCall(): Flow<RemoteResult<DTOType>>
    
    protected abstract suspend fun onFetchSuccess(data: DTOType): DomainType
    
    fun asFlow(): Flow<Result<DomainType>> {
        return result
    }
    
}