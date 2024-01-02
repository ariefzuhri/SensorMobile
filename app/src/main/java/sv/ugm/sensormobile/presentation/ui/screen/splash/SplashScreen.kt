package sv.ugm.sensormobile.presentation.ui.screen.splash

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import sv.ugm.sensormobile.presentation.ui.designsystem.component.Icon
import sv.ugm.sensormobile.presentation.ui.designsystem.icon.SensorMobileIcons

@Composable
fun SplashScreen(
    navigateToLogin: () -> Unit,
    navigateToDashboard: () -> Unit,
    startSensorAutoUpdateService: () -> Unit,
    stopSensorAutoUpdateService: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    val state = viewModel.state.value
    
    SplashContent(
        state = state,
        navigateToLogin = navigateToLogin,
        navigateToDashboard = navigateToDashboard,
        startSensorAutoUpdateService = startSensorAutoUpdateService,
        stopSensorAutoUpdateService = stopSensorAutoUpdateService,
    )
}

@Composable
private fun SplashContent(
    state: SplashState,
    navigateToLogin: () -> Unit,
    navigateToDashboard: () -> Unit,
    startSensorAutoUpdateService: () -> Unit,
    stopSensorAutoUpdateService: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize(),
    ) {
        SplashSection()
    }
    
    CheckLoginSession(
        state = state,
        navigateToLogin = navigateToLogin,
        navigateToDashboard = navigateToDashboard,
        startSensorAutoUpdateService = startSensorAutoUpdateService,
        stopSensorAutoUpdateService = stopSensorAutoUpdateService,
    )
}

@Composable
private fun SplashSection() {
    Icon(
        icon = SensorMobileIcons.Logo,
        size = 80.dp,
    )
}

@Composable
private fun CheckLoginSession(
    state: SplashState,
    navigateToLogin: () -> Unit,
    navigateToDashboard: () -> Unit,
    startSensorAutoUpdateService: () -> Unit,
    stopSensorAutoUpdateService: () -> Unit,
) {
    LaunchedEffect(state.isLoggedIn) {
        if (!state.isLoading) {
            if (state.isLoggedIn == false) {
                navigateToLogin()
                stopSensorAutoUpdateService()
            } else {
                navigateToDashboard()
                startSensorAutoUpdateService()
            }
            
        }
    }
}