package sv.ugm.sensormobile.presentation.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import sv.ugm.sensormobile.presentation.ui.route.NavGraph

@Composable
fun SensorMobileApp(
    navController: NavHostController = rememberNavController(),
) {
    Scaffold { innerPadding ->
        NavGraph(
            navController = navController,
            modifier = Modifier
                .padding(innerPadding),
        )
    }
}