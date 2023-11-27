package sv.ugm.sensormobile.ui.designsystem.component

import androidx.compose.runtime.Composable
import com.patrykandpatrick.vico.core.legend.HorizontalLegend
import sv.ugm.sensormobile.ui.util.rememberLegend

@Composable
fun chartLegend(
    vararg labels: String,
): HorizontalLegend? {
    return rememberLegend(
        labels = labels,
    )
}