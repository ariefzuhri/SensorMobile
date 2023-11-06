package sv.ugm.sensormobile.ui.screen.dashboard

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.domain.util.Constants
import sv.ugm.sensormobile.ui.designsystem.component.LineChart
import sv.ugm.sensormobile.ui.designsystem.component.NavDrawer
import sv.ugm.sensormobile.ui.designsystem.component.TopBar
import sv.ugm.sensormobile.ui.util.CONTAINER_PADDING_DP
import sv.ugm.sensormobile.ui.util.DashboardNavDrawerItem
import sv.ugm.sensormobile.ui.util.DateTimeValueFormatter
import sv.ugm.sensormobile.ui.util.LockScreenOrientation
import sv.ugm.sensormobile.ui.util.SensorRecordMarkerLabelFormatter
import sv.ugm.sensormobile.ui.util.asToast
import sv.ugm.sensormobile.ui.util.load
import sv.ugm.sensormobile.ui.util.rememberMarkerLabelFormatter
import sv.ugm.sensormobile.ui.util.rememberValueFormatter

@Composable
fun DashboardScreen(
    navigateToLogin: () -> Unit,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    scope: CoroutineScope = rememberCoroutineScope(),
    viewModel: DashboardViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    
    if (state.isLoggedIn == true) {
        DashboardContent(
            drawerState = drawerState,
            scope = scope,
            state = state,
            viewModel = viewModel,
        )
        
        val context = LocalContext.current
        LaunchedEffect(state.failureMessage) {
            state.failureMessage.asToast(context)
        }
        
        BackHandler(enabled = drawerState.isOpen) {
            scope.launch { drawerState.close() }
        }
        
        LockScreenOrientation(
            orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE,
        )
    } else {
        CheckLoginSession(
            state = state,
            navigateToLogin = navigateToLogin,
        )
    }
}

@Composable
fun CheckLoginSession(
    state: DashboardState,
    navigateToLogin: () -> Unit,
) {
    LaunchedEffect(state.isLoggedIn) {
        if (state.isLoggedIn == false) {
            navigateToLogin()
        }
    }
}

@Composable
private fun DashboardContent(
    drawerState: DrawerState,
    scope: CoroutineScope,
    state: DashboardState,
    viewModel: DashboardViewModel,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = stringResource(
                    id = R.string.txt_title_dashboard,
                    state.graphTitle.load(),
                ),
                menuContDesc = R.string.btn_cd_menu_topbar_dashboard.load(),
                onMenuClick = {
                    scope.launch {
                        if (drawerState.isOpen) drawerState.close()
                        else drawerState.open()
                    }
                },
            )
        },
    ) { innerPadding ->
        NavDrawer(
            drawerState = drawerState,
            scope = scope,
            itemList = state.navDrawerItemList,
            onItemSelected = { item ->
                viewModel.onEvent(
                    DashboardEvent.OnSensorTypeSelected(
                        sensorType = (item as DashboardNavDrawerItem).sensorType,
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
                GraphSection(state)
            }
        }
    }
}

@Composable
private fun GraphSection(
    state: DashboardState,
) {
    val xAxisLabelFormatter = rememberValueFormatter(
        DateTimeValueFormatter(
            datePattern = Constants.DateTimePatterns.Formatted.SHORT_DATE,
            timePattern = Constants.DateTimePatterns.Formatted.SHORT_TIME,
        )
    )
    val markerLabelFormatter = rememberMarkerLabelFormatter(
        SensorRecordMarkerLabelFormatter()
    )
    
    LineChart(
        dataset = state.chartDataset,
        xAxisValueFormatter = xAxisLabelFormatter,
        markerLabelFormatter = markerLabelFormatter,
        modifier = Modifier
            .fillMaxSize(),
    )
}