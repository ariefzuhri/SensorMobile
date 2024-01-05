package sv.ugm.sensormobile.presentation.ui.screen.splash

import android.Manifest
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import sv.ugm.sensormobile.presentation.ui.designsystem.component.Badge
import sv.ugm.sensormobile.presentation.ui.designsystem.component.Icon
import sv.ugm.sensormobile.presentation.ui.designsystem.icon.SensorMobileIcons
import sv.ugm.sensormobile.presentation.util.CONTAINER_PADDING_DP
import sv.ugm.sensormobile.presentation.util.showToast

@Composable
fun SplashScreen(
    navigateToLogin: () -> Unit,
    navigateToDashboard: () -> Unit,
    startSensorMonitoringService: () -> Unit,
    stopSensorMonitoringService: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    
    SplashContent(
        state = state,
    )
    
    CheckLoginSession(
        state = state,
        navigateToLogin = navigateToLogin,
        navigateToDashboard = navigateToDashboard,
        startSensorMonitoringService = startSensorMonitoringService,
        stopSensorMonitoringService = stopSensorMonitoringService,
    )
    
    CheckNotificationPermission()
}

@Composable
private fun SplashContent(
    state: SplashState,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(CONTAINER_PADDING_DP.dp),
    ) {
        SplashSection(
            state = state,
        )
    }
}

@Composable
private fun SplashSection(
    state: SplashState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Icon(
            icon = SensorMobileIcons.Logo,
            size = 100.dp,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(
                    bottom = 75.dp,
                ),
        )
        Badge(
            text = state.appVersion,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(
                    bottom = 16.dp,
                ),
        )
    }
}

@Composable
private fun CheckLoginSession(
    state: SplashState,
    navigateToLogin: () -> Unit,
    navigateToDashboard: () -> Unit,
    startSensorMonitoringService: () -> Unit,
    stopSensorMonitoringService: () -> Unit,
) {
    LaunchedEffect(state.isLoggedIn) {
        if (!state.isLoading) {
            if (state.isLoggedIn == false) {
                navigateToLogin()
                stopSensorMonitoringService()
            } else {
                navigateToDashboard()
                startSensorMonitoringService()
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CheckNotificationPermission() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val context = LocalContext.current
        
        val notificationPermissionState = rememberPermissionState(
            Manifest.permission.POST_NOTIFICATIONS,
        )
        
        if (notificationPermissionState.status.isGranted.not()) {
            SideEffect {
                showToast(
                    context = context,
                    message = "The app needs notification permission to work properly.",
                )
                notificationPermissionState.launchPermissionRequest()
            }
        }
    }
}