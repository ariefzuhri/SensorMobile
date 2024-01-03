package sv.ugm.sensormobile.presentation.ui.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.presentation.ui.designsystem.icon.SensorMobileIcons
import sv.ugm.sensormobile.presentation.ui.designsystem.theme.SensorMobileTheme
import sv.ugm.sensormobile.presentation.util.load

@Composable
fun Card(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(
                elevation = 20.dp,
                shape = MaterialTheme.shapes.medium,
                ambientColor = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                spotColor = MaterialTheme.colorScheme.onSurface.copy(0.5f),
            )
            .clip(MaterialTheme.shapes.medium)
            .background(
                color = MaterialTheme.colorScheme.background,
                shape = MaterialTheme.shapes.medium,
            )
            .clickable(onClick != null) { onClick!!() }
            .padding(
                horizontal = 10.dp,
                vertical = 18.dp,
            ),
    ) {
        content()
    }
}

@Composable
fun SensorSummaryCard(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
    value: String?,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    icon = icon,
                )
                Spacer(modifier = Modifier.size(2.dp))
                Text(
                    text = title,
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            if (value != null) {
                Text(
                    text = value,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall,
                )
            } else {
                DataLoading(
                    size = MaterialTheme.typography.headlineSmall.fontSize.value.dp,
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardPreview() {
    SensorMobileTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
        ) {
            SensorSummaryCard(
                title = R.string.menu_humidity_chart.load(),
                icon = SensorMobileIcons.HumiditySensor,
                value = "80%",
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth(0.45f),
            )
        }
    }
}