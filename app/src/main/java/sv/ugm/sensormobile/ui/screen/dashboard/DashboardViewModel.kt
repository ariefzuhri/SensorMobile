package sv.ugm.sensormobile.ui.screen.dashboard

import androidx.annotation.StringRes
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sv.ugm.sensormobile.domain.usecase.CheckLoginSessionUseCase
import sv.ugm.sensormobile.domain.usecase.GetSensorRecordsUseCase
import sv.ugm.sensormobile.domain.util.Result
import sv.ugm.sensormobile.domain.util.SensorType
import sv.ugm.sensormobile.ui.mapper.SensorRecordDataMapper
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val checkLoginSessionUseCase: CheckLoginSessionUseCase,
    private val getSensorRecordsUseCase: GetSensorRecordsUseCase,
    private val mapper: SensorRecordDataMapper,
) : ViewModel() {
    
    private val _state = mutableStateOf(DashboardState())
    val state: State<DashboardState> get() = _state
    
    init {
        checkLoginSession()
        getSensorRecords(
            sensorType = _state.value.selectedSensorType,
        )
    }
    
    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.OnSensorTypeSelected -> {
                getSensorRecords(
                    sensorType = event.sensorType,
                )
            }
        }
    }
    
    private fun checkLoginSession() {
        viewModelScope.launch {
            checkLoginSessionUseCase().collect { isLoggedIn ->
                _state.value = _state.value.copy(
                    isLoggedIn = isLoggedIn,
                )
            }
        }
    }
    
    private fun getSensorRecords(
        sensorType: SensorType,
    ) {
        viewModelScope.launch {
            getSensorRecordsUseCase(
                sensorType = sensorType,
            ).collect { result ->
                @StringRes val sensorRecordName = _state.value.navDrawerItemList
                    .first { it.sensorType == sensorType }.title
                
                when (result) {
                    is Result.Loading -> {
                        _state.value = _state.value.copy(
                            selectedSensorType = sensorType,
                            graphTitle = sensorRecordName,
                            isLoading = true,
                        )
                    }
                    
                    is Result.Success -> {
                        _state.value = _state.value.copy(
                            selectedSensorType = sensorType,
                            graphTitle = sensorRecordName,
                            chartDataset = mapper.mapToChartDataset(
                                input = result.data,
                                label = sensorRecordName,
                            ),
                            isLoading = false,
                        )
                    }
                    
                    is Result.Failure -> {
                        _state.value = _state.value.copy(
                            selectedSensorType = sensorType,
                            graphTitle = sensorRecordName,
                            isLoading = false,
                            failureMessage = result.message,
                        )
                    }
                }
            }
        }
    }
    
}