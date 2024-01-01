package sv.ugm.sensormobile.presentation.ui.designsystem.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import sv.ugm.sensormobile.presentation.ui.designsystem.icon.SensorMobileIcons
import sv.ugm.sensormobile.presentation.util.toPainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    menuContDesc: String? = null,
    onMenuClick: (() -> Unit)? = null,
) {
    TopAppBar(
        navigationIcon = {
            if (onMenuClick != null) {
                IconButton(
                    onClick = onMenuClick,
                ) {
                    Icon(
                        painter = SensorMobileIcons.Menu.toPainter(),
                        contentDescription = menuContDesc,
                    )
                }
            }
        },
        title = {
            Text(
                text = title,
            )
        },
        modifier = modifier,
    )
}