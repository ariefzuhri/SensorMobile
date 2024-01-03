package sv.ugm.sensormobile.presentation.ui.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.ugm.sensormobile.presentation.ui.designsystem.icon.SensorMobileIcons
import sv.ugm.sensormobile.presentation.ui.designsystem.theme.SensorMobileTheme

@Composable
private fun BaseButton(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
    colors: ButtonColors,
    iconTint: Color,
    onClick: () -> Unit,
    isLoading: Boolean = false,
    enabled: Boolean = true,
) {
    Button(
        shape = CircleShape,
        colors = colors,
        enabled = enabled,
        onClick = if (!isLoading) onClick else ({}),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(
                    vertical = 6.dp,
                ),
        ) {
            if (!isLoading) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelLarge,
                )
                Spacer(modifier = Modifier.size(6.dp))
                Icon(
                    icon = icon,
                    size = 16.dp,
                    tint = if (enabled) iconTint
                    else MaterialTheme.colorScheme.outline.copy(0.5f),
                )
            } else {
                CircularProgressIndicator(
                    color = iconTint,
                    modifier = Modifier
                        .size(16.dp)
                        .align(Alignment.CenterVertically),
                )
            }
        }
    }
}

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    isLoading: Boolean = false,
    enabled: Boolean = true,
) {
    BaseButton(
        title = title,
        icon = icon,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        iconTint = MaterialTheme.colorScheme.onPrimary.copy(0.8f),
        isLoading = isLoading,
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
    )
}

@Composable
fun TertiaryButton(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    isLoading: Boolean = false,
    enabled: Boolean = true,
) {
    BaseButton(
        title = title,
        icon = icon,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.outline,
        ),
        iconTint = MaterialTheme.colorScheme.outline.copy(0.8f),
        isLoading = isLoading,
        enabled = enabled,
        onClick = onClick,
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun PrimaryButtonPreview() {
    SensorMobileTheme {
        PrimaryButton(
            title = "Log in",
            icon = SensorMobileIcons.Login,
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TertiaryButtonPreview() {
    SensorMobileTheme {
        TertiaryButton(
            title = "Log out",
            icon = SensorMobileIcons.Logout,
            onClick = {},
        )
    }
}