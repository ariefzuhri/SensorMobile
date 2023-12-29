package sv.ugm.sensormobile.ui.route

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import sv.ugm.sensormobile.ui.screen.chart.ChartScreen
import sv.ugm.sensormobile.ui.screen.login.LoginScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Chart.route,
        modifier = modifier,
    ) {
        composable(Screen.Chart.route) {
            ChartScreen(
                navigateToLogin = {
                    navController.navigate(Screen.Login.route) {
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
                    navController.navigate(Screen.Chart.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
                },
            )
        }
    }
}