package sv.ugm.sensormobile.ui.designsystem.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button as MaterialButton

@Composable
fun Button(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    MaterialButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = title,
        )
    }
}