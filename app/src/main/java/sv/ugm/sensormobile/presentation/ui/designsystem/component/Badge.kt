package sv.ugm.sensormobile.presentation.ui.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.ugm.sensormobile.presentation.ui.designsystem.icon.SensorMobileIcons
import sv.ugm.sensormobile.presentation.ui.designsystem.theme.SensorMobileTheme

@Composable
fun Badge(
    modifier: Modifier = Modifier,
    text: String,
    @DrawableRes icon: Int? = null,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(
                shape = MaterialTheme.shapes.small,
            )
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = CircleShape,
            )
            .padding(
                top = 2.dp,
                bottom = 2.dp,
                start = if (icon != null) 3.dp else 6.dp,
                end = 6.dp,
            ),
    ) {
        if (icon != null) {
            Icon(
                icon = icon,
                size = 16.dp,
                tint = MaterialTheme.colorScheme.onSecondary.copy(0.8f),
            )
            Spacer(modifier = Modifier.size(2.dp))
        }
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BadgePreview() {
    SensorMobileTheme {
        Badge(
            text = "auto-updates",
            icon = SensorMobileIcons.Check,
        )
    }
}