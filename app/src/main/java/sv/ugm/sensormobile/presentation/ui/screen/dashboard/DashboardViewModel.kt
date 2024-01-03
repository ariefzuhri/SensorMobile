package sv.ugm.sensormobile.presentation.ui.screen.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sv.ugm.sensormobile.domain.usecase.GetSensorSummaryUseCase
import sv.ugm.sensormobile.domain.usecase.LogOutUseCase
import sv.ugm.sensormobile.domain.util.Result
import sv.ugm.sensormobile.presentation.mapper.SensorSummaryPresentationMapper
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val getSensorSummaryUseCase: GetSensorSummaryUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val mapper: SensorSummaryPresentationMapper,
) : ViewModel() {
    
    private val _state = mutableStateOf(DashboardState())
    val state: State<DashboardState> get() = _state
    
    init {
        getSensorSummary()
    }
    
    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.LogOut -> {
                logOut()
            }
        }
    }
    
    private fun getSensorSummary() {
        viewModelScope.launch {
            getSensorSummaryUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            sensorSummary = mapper.mapDomainToPresentation(
                                result.data,
                            ),
                        )
                    }
                    
                    else -> {}
                }
            }
        }
    }
    
    private fun logOut() {
        viewModelScope.launch {
            logOutUseCase()
        }
    }
    
}