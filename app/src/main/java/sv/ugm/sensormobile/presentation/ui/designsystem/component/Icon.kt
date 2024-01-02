package sv.ugm.sensormobile.presentation.ui.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import sv.ugm.sensormobile.presentation.util.toPainter
import androidx.compose.material3.Icon as MaterialIcon

@Composable
fun Icon(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    size: Dp = 24.dp,
    contentDescription: String? = null,
    tint: Color = MaterialTheme.colorScheme.primary.copy(0.8f),
) {
    MaterialIcon(
        painter = icon.toPainter(),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier
            .size(size),
    )
}