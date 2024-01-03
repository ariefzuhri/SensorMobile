package sv.ugm.sensormobile.presentation.ui.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import sv.ugm.sensormobile.presentation.ui.designsystem.icon.SensorMobileIcons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: (() -> Unit)? = null,
    onBackContDesc: String? = null,
    onMenuClick: (() -> Unit)? = null,
    onMenuContDesc: String? = null,
) {
    TopAppBar(
        navigationIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (onBackClick != null) {
                    IconButton(
                        onClick = onBackClick,
                    ) {
                        Icon(
                            icon = SensorMobileIcons.Back,
                            contentDescription = onBackContDesc,
                        )
                    }
                }
                if (onMenuClick != null) {
                    IconButton(
                        onClick = onMenuClick,
                    ) {
                        Icon(
                            icon = SensorMobileIcons.Menu,
                            contentDescription = onMenuContDesc,
                        )
                    }
                }
            }
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
            )
        },
        modifier = modifier,
    )
}