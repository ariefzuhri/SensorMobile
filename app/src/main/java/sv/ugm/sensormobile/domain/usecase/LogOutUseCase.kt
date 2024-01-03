package sv.ugm.sensormobile.domain.usecase

import sv.ugm.sensormobile.domain.repository.IAuthRepository
import sv.ugm.sensormobile.domain.repository.ISensorOutputRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val authRepository: IAuthRepository,
    private val sensorOutputRepository: ISensorOutputRepository,
) {
    
    suspend operator fun invoke() {
        authRepository.clearLoginSession()
        sensorOutputRepository.clearSensorOutputsLocal()
    }
    
}