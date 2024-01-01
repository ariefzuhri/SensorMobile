package sv.ugm.sensormobile.presentation.ui.screen.chart

import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sv.ugm.sensormobile.domain.usecase.CheckLoginSessionUseCase
import sv.ugm.sensormobile.domain.usecase.GetSensorDataUseCase
import sv.ugm.sensormobile.domain.util.Result
import sv.ugm.sensormobile.domain.util.SensorDataType
import sv.ugm.sensormobile.presentation.mapper.SensorOutputPresentationMapper
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val checkLoginSessionUseCase: CheckLoginSessionUseCase,
    private val getSensorDataUseCase: GetSensorDataUseCase,
    private val mapper: SensorOutputPresentationMapper,
) : ViewModel() {
    
    private val _state = mutableStateOf(ChartState())
    val state: State<ChartState> get() = _state
    
    init {
        checkLoginSession()
        getSensorData(
            sensorDataType = _state.value.navDrawerItemList.first().sensorDataType,
        )
    }
    
    fun onEvent(event: ChartEvent) {
        when (event) {
            is ChartEvent.OnSensorDataTypeSelected -> {
                getSensorData(
                    sensorDataType = event.sensorDataType,
                )
            }
        }
    }
    
    private fun checkLoginSession() {
        viewModelScope.launch {
            checkLoginSessionUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            isLoggedIn = result.data,
                        )
                    }
                    
                    else -> {}
                }
            }
        }
    }
    
    private fun getSensorData(
        sensorDataType: SensorDataType,
    ) {
        viewModelScope.launch {
            getSensorDataUseCase(
                sensorDataType = sensorDataType,
            ).collect { result ->
                @StringRes val sensorDataName = _state.value.navDrawerItemList
                    .first { it.sensorDataType == sensorDataType }.title
                
                when (result) {
                    is Result.Loading -> {
                        _state.value = _state.value.copy(
                            chartTitle = sensorDataName,
                            isLoading = true,
                        )
                    }
                    
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            chartTitle = sensorDataName,
                            chartData = mapper.mapDomainToPresentation(
                                input = result.data,
                                label = sensorDataName,
                            ),
                            isLoading = false,
                        )
                    }
                    
                    is Result.Failure -> {
                        _state.value = _state.value.copy(
                            chartTitle = sensorDataName,
                            isLoading = false,
                            failureMessage = mutableStateOf(result.message),
                        )
                    }
                }
            }
        }
    }
    
}