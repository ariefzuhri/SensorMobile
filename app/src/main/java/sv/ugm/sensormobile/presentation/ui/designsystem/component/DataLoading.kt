package sv.ugm.sensormobile.presentation.ui.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun DataLoading(
    modifier: Modifier = Modifier,
    size: Dp = 16.dp,
) {
    CircularProgressIndicator(
        color = MaterialTheme.colorScheme.outlineVariant.copy(0.5f),
        modifier = modifier
            .size(size),
    )
}