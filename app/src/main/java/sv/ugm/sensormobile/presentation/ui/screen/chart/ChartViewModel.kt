package sv.ugm.sensormobile.presentation.ui.screen.chart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sv.ugm.sensormobile.domain.usecase.GetSensorDataUseCase
import sv.ugm.sensormobile.domain.util.Result
import sv.ugm.sensormobile.domain.util.SensorDataType
import sv.ugm.sensormobile.presentation.mapper.SensorOutputPresentationMapper
import javax.inject.Inject

@HiltViewModel
class ChartViewModel @Inject constructor(
    private val getSensorDataUseCase: GetSensorDataUseCase,
    private val mapper: SensorOutputPresentationMapper,
) : ViewModel() {
    
    private val _state = mutableStateOf(ChartState())
    val state: State<ChartState> get() = _state
    
    fun onEvent(event: ChartEvent) {
        when (event) {
            is ChartEvent.Init -> {
                init(
                    sensorDataTypeId = event.sensorDataTypeId,
                )
            }
            
            is ChartEvent.OnSensorDataTypeSelected -> {
                getSensorData(
                    sensorDataType = event.sensorDataType,
                )
            }
        }
    }
    
    private fun init(sensorDataTypeId: Int) {
        getSensorData(
            sensorDataType = when (sensorDataTypeId) {
                SensorDataType.AirQuality.id -> SensorDataType.AirQuality
                SensorDataType.ApproxAltitude.id -> SensorDataType.ApproxAltitude
                SensorDataType.Humidity.id -> SensorDataType.Humidity
                SensorDataType.Light.id -> SensorDataType.Light
                SensorDataType.Pressure.id -> SensorDataType.Pressure
                SensorDataType.Raindrop.id -> SensorDataType.Raindrop
                SensorDataType.SoilMoisture.id -> SensorDataType.SoilMoisture
                SensorDataType.Temperature1.id -> SensorDataType.Temperature1
                SensorDataType.Temperature2.id -> SensorDataType.Temperature2
                else -> SensorDataType.Light
            },
        )
    }
    
    private fun getSensorData(
        sensorDataType: SensorDataType,
    ) {
        viewModelScope.launch {
            getSensorDataUseCase(
                sensorDataType = sensorDataType,
            ).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true,
                        )
                    }
                    
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            selectedSensorDataType = sensorDataType,
                            chartData = mapper.mapDomainToPresentation(
                                input = result.data,
                                sensorDataType = sensorDataType,
                            ),
                        )
                    }
                    
                    is Result.Empty -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                        )
                    }
                    
                    is Result.Failure -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            failureMessage = mutableStateOf(result.message),
                        )
                    }
                }
            }
        }
    }
    
}