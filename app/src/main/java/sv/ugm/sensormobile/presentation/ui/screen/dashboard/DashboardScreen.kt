package sv.ugm.sensormobile.presentation.ui.screen.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.domain.util.SensorDataType
import sv.ugm.sensormobile.presentation.ui.designsystem.component.Badge
import sv.ugm.sensormobile.presentation.ui.designsystem.component.DataLoading
import sv.ugm.sensormobile.presentation.ui.designsystem.component.Icon
import sv.ugm.sensormobile.presentation.ui.designsystem.component.SensorSummaryCard
import sv.ugm.sensormobile.presentation.ui.designsystem.component.TertiaryButton
import sv.ugm.sensormobile.presentation.ui.designsystem.icon.SensorMobileIcons
import sv.ugm.sensormobile.presentation.util.CONTAINER_PADDING_DP
import sv.ugm.sensormobile.presentation.util.load

@Composable
fun DashboardScreen(
    navigateToChart: (Int) -> Unit,
    restartApp: () -> Unit,
    viewModel: DashboardViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    
    DashboardContent(
        navigateToChart = navigateToChart,
        restartApp = restartApp,
        state = state,
        viewModel = viewModel,
    )
}

@Composable
private fun DashboardContent(
    navigateToChart: (Int) -> Unit,
    restartApp: () -> Unit,
    state: DashboardState,
    viewModel: DashboardViewModel,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    navigateToChart(SensorDataType.Light.id)
                },
                modifier = Modifier
                    .padding(8.dp),
            ) {
                Icon(
                    icon = SensorMobileIcons.Chart,
                    contentDescription = R.string.btn_cd_view_chart_dashboard.load(),
                    tint = MaterialTheme.colorScheme.onPrimary.copy(0.8f),
                )
            }
        },
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(CONTAINER_PADDING_DP.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            profileSection(
                state = state,
            )
            
            summarySection(
                state = state,
                navigateToChart = navigateToChart,
            )
            
            logoutSection(
                restartApp = restartApp,
                viewModel = viewModel,
            )
        }
    }
}

private fun LazyGridScope.profileSection(
    state: DashboardState,
) {
    item(span = { GridItemSpan(2) }) {
        Column {
            Text(
                text = stringResource(
                    R.string.txt_welcome_dashboard,
                    state.userName,
                ),
                style = MaterialTheme.typography.headlineMedium,
            )
            
            Spacer(modifier = Modifier.size(48.dp))
        }
    }
}

private fun LazyGridScope.summarySection(
    state: DashboardState,
    navigateToChart: (Int) -> Unit,
) {
    item(span = { GridItemSpan(2) }) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    icon = SensorMobileIcons.Summary,
                    size = 32.dp,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = R.string.txt_title_summary_dashboard.load(),
                    style = MaterialTheme.typography.headlineSmall,
                )
                Spacer(modifier = Modifier.size(12.dp))
                Divider(
                    thickness = (0.5).dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(0.5f),
                    modifier = Modifier
                        .width(56.dp)
                )
            }
            
            Spacer(modifier = Modifier.size(16.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = R.string.txt_last_data_dashboard.load(),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .padding(end = 4.dp),
                )
                state.sensorSummary.latestTimestampMillis?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Badge(
                        text = R.string.txt_auto_update_dashboard.load(),
                        icon = SensorMobileIcons.Check,
                    )
                } ?: DataLoading()
            }
            
            Spacer(modifier = Modifier.size(24.dp))
        }
    }
    
    items(
        items = listOf(
            state.sensorSummary.latestLight,
            state.sensorSummary.latestSoilMoisture,
            state.sensorSummary.latestAirQuality,
            state.sensorSummary.latestRaindrop,
            state.sensorSummary.latestHumidity,
            state.sensorSummary.latestTemperature1,
            state.sensorSummary.latestTemperature2,
            state.sensorSummary.latestPressure,
            state.sensorSummary.latestApproxAltitude,
        ),
        key = { item -> item.type.id },
    ) { item ->
        SensorSummaryCard(
            title = when (item.type) {
                SensorDataType.Light -> R.string.txt_summary_light_dashboard
                SensorDataType.SoilMoisture -> R.string.txt_summary_soil_moisture_dashboard
                SensorDataType.AirQuality -> R.string.txt_summary_air_quality_dashboard
                SensorDataType.Raindrop -> R.string.txt_summary_raindrop_dashboard
                SensorDataType.Humidity -> R.string.txt_summary_humidity_dashboard
                SensorDataType.Temperature1 -> R.string.txt_summary_temperature_1_dashboard
                SensorDataType.Temperature2 -> R.string.txt_summary_temperature_2_dashboard
                SensorDataType.Pressure -> R.string.txt_summary_pressure_dashboard
                SensorDataType.ApproxAltitude -> R.string.txt_summary_approx_altitude_dashboard
            }.load(),
            icon = when (item.type) {
                SensorDataType.Light -> SensorMobileIcons.LightSensor
                SensorDataType.SoilMoisture -> SensorMobileIcons.SoilMoistureSensor
                SensorDataType.AirQuality -> SensorMobileIcons.AirQualitySensor
                SensorDataType.Raindrop -> SensorMobileIcons.RaindropSensor
                SensorDataType.Humidity -> SensorMobileIcons.HumiditySensor
                SensorDataType.Temperature1 -> SensorMobileIcons.Temperature1Sensor
                SensorDataType.Temperature2 -> SensorMobileIcons.Temperature2Sensor
                SensorDataType.Pressure -> SensorMobileIcons.PressureSensor
                SensorDataType.ApproxAltitude -> SensorMobileIcons.ApproxAltitudeSensor
            },
            value = when (item.type) {
                SensorDataType.Light,
                SensorDataType.AirQuality,
                SensorDataType.Raindrop,
                SensorDataType.Temperature1,
                SensorDataType.Temperature2,
                SensorDataType.Pressure,
                SensorDataType.ApproxAltitude,
                -> "${item.value} ${item.unit}"
                
                SensorDataType.SoilMoisture,
                SensorDataType.Humidity,
                -> "${item.value}${item.unit}"
            }.takeIf { item.value != null },
            onClick = {
                navigateToChart(item.type.id)
            },
        )
    }
}

private fun LazyGridScope.logoutSection(
    restartApp: () -> Unit,
    viewModel: DashboardViewModel,
) {
    item(span = { GridItemSpan(2) }) {
        Column {
            Spacer(modifier = Modifier.size(48.dp))
            
            TertiaryButton(
                title = R.string.btn_log_out_dashboard.load(),
                icon = SensorMobileIcons.Logout,
                onClick = {
                    viewModel.onEvent(DashboardEvent.LogOut)
                    restartApp()
                },
                modifier = Modifier
                    .fillMaxSize(),
            )
        }
    }
}