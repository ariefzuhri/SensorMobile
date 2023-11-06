package sv.ugm.sensormobile.ui.designsystem.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
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
                        imageVector = Icons.Rounded.Menu,
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