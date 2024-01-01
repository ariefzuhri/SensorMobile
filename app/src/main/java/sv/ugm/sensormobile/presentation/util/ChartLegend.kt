package sv.ugm.sensormobile.presentation.util

import android.graphics.Typeface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.patrykandpatrick.vico.compose.component.shapeComponent
import com.patrykandpatrick.vico.compose.component.textComponent
import com.patrykandpatrick.vico.compose.dimensions.dimensionsOf
import com.patrykandpatrick.vico.compose.legend.horizontalLegend
import com.patrykandpatrick.vico.compose.legend.legendItem
import com.patrykandpatrick.vico.compose.style.currentChartStyle
import com.patrykandpatrick.vico.core.component.shape.Shapes
import com.patrykandpatrick.vico.core.legend.HorizontalLegend

private val legendItemLabelTextSize = 12.sp
private val legendItemIconSize = 8.dp
private val legendItemIconPaddingValue = 4.dp
private val legendItemSpacing = 8.dp
private val legendTopPaddingValue = 2.dp
private val legendPadding = dimensionsOf(top = legendTopPaddingValue)

@Composable
fun rememberLegend(
    chartColors: List<Color> = defaultChartColors(),
    vararg labels: String,
): HorizontalLegend? {
    return horizontalLegend(
        items = labels.mapIndexed { index, title ->
            legendItem(
                icon = shapeComponent(
                    Shapes.pillShape,
                    chartColors[index % chartColors.size],
                ),
                label = textComponent(
                    color = currentChartStyle.axis.axisLabelColor,
                    textSize = legendItemLabelTextSize,
                    typeface = Typeface.MONOSPACE,
                ),
                labelText = title
            )
        },
        iconSize = legendItemIconSize,
        iconPadding = legendItemIconPaddingValue,
        spacing = legendItemSpacing,
        padding = legendPadding,
    ).takeIf { labels.isNotEmpty() && chartColors.isNotEmpty() }
}