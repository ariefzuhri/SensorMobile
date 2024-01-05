package sv.ugm.sensormobile.presentation.ui.screen.chart

import android.content.pm.ActivityInfo
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.presentation.ui.designsystem.component.LineChart
import sv.ugm.sensormobile.presentation.ui.designsystem.component.NavDrawer
import sv.ugm.sensormobile.presentation.ui.designsystem.component.TopBar
import sv.ugm.sensormobile.presentation.ui.designsystem.component.chartLegend
import sv.ugm.sensormobile.presentation.util.CONTAINER_PADDING_DP
import sv.ugm.sensormobile.presentation.util.ChartNavDrawerItem
import sv.ugm.sensormobile.presentation.util.LockScreenOrientation
import sv.ugm.sensormobile.presentation.util.load

@Composable
fun ChartScreen(
    onBack: () -> Unit,
    sensorDataTypeId: Int,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    scope: CoroutineScope = rememberCoroutineScope(),
    viewModel: ChartViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    
    LaunchedEffect(sensorDataTypeId) {
        viewModel.onEvent(
            ChartEvent.Init(
                sensorDataTypeId = sensorDataTypeId,
            )
        )
    }
    
    ChartContent(
        drawerState = drawerState,
        scope = scope,
        state = state,
        viewModel = viewModel,
        onBack = onBack,
    )
    
    BackHandler(enabled = drawerState.isOpen) {
        scope.launch { drawerState.close() }
    }
    
    LockScreenOrientation(
        orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,
    )
}

@Composable
private fun ChartContent(
    drawerState: DrawerState,
    scope: CoroutineScope,
    state: ChartState,
    viewModel: ChartViewModel,
    onBack: () -> Unit,
) {
    val navDrawerItemList = remember {
        listOf(
            ChartNavDrawerItem.LightChart,
            ChartNavDrawerItem.SoilMoistureChart,
            ChartNavDrawerItem.AirQualityChart,
            ChartNavDrawerItem.RaindropChart,
            ChartNavDrawerItem.HumidityChart,
            ChartNavDrawerItem.Temperature1Chart,
            ChartNavDrawerItem.Temperature2Chart,
            ChartNavDrawerItem.PressureChart,
            ChartNavDrawerItem.ApproxAltitudeChart,
        )
    }
    val selectedSensorDataTypeName = remember(state.selectedSensorDataType) {
        navDrawerItemList.first {
            it.sensorDataType == state.selectedSensorDataType
        }.title
    }.load()
    
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(
                    id = R.string.txt_title_chart,
                    selectedSensorDataTypeName,
                ),
                onBackClick = {
                    onBack()
                },
                onBackContDesc = R.string.btn_cd_back_chart.load(),
                onMenuClick = {
                    scope.launch {
                        if (drawerState.isOpen) drawerState.close()
                        else drawerState.open()
                    }
                },
                onMenuContDesc = R.string.btn_cd_menu_topbar_chart.load(),
            )
        },
    ) { innerPadding ->
        NavDrawer(
            drawerState = drawerState,
            scope = scope,
            itemList = navDrawerItemList,
            onItemSelected = { item ->
                viewModel.onEvent(
                    ChartEvent.OnSensorDataTypeSelected(
                        sensorDataType = (item as ChartNavDrawerItem).sensorDataType,
                    )
                )
            },
            modifier = Modifier
                .padding(innerPadding),
        ) {
            Column(
                modifier = Modifier
                    .padding(CONTAINER_PADDING_DP.dp),
            ) {
                ChartSection(
                    state = state,
                    selectedSensorDataTypeName = selectedSensorDataTypeName,
                )
            }
        }
    }
}

@Composable
private fun ChartSection(
    state: ChartState,
    selectedSensorDataTypeName: String,
) {
    val chartLegend = chartLegend(
        selectedSensorDataTypeName,
    )
    LineChart(
        series = state.chartData,
        showDataPoint = false,
        legend = chartLegend,
        modifier = Modifier
            .fillMaxSize(),
    )
}