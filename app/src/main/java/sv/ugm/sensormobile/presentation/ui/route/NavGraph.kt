package sv.ugm.sensormobile.presentation.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import sv.ugm.sensormobile.presentation.ui.screen.chart.ChartScreen
import sv.ugm.sensormobile.presentation.ui.screen.dashboard.DashboardScreen
import sv.ugm.sensormobile.presentation.ui.screen.login.LoginScreen
import sv.ugm.sensormobile.presentation.ui.screen.splash.SplashScreen
import sv.ugm.sensormobile.presentation.util.startSensorAutoUpdateService
import sv.ugm.sensormobile.presentation.util.stopSensorAutoUpdateService

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier,
    ) {
        composable(Screen.Chart.route) {
            ChartScreen()
        }
        
        composable(Screen.Dashboard.route) {
            DashboardScreen(
                navigateToChart = {
                    navController.navigate(Screen.Chart.route)
                },
                restartApp = {
                    navController.navigate(Screen.Splash.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        
        composable(Screen.Login.route) {
            LoginScreen(
                restartApp = {
                    navController.navigate(Screen.Splash.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
            )
        }
        
        composable(Screen.Splash.route) {
            SplashScreen(
                navigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                navigateToDashboard = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                startSensorAutoUpdateService = {
                    startSensorAutoUpdateService(context)
                },
                stopSensorAutoUpdateService = {
                    stopSensorAutoUpdateService(context)
                }
            )
        }
    }
}