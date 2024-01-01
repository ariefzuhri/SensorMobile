package sv.ugm.sensormobile.presentation.ui.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import sv.ugm.sensormobile.presentation.util.toPainter
import androidx.compose.material3.TextField as MaterialTextField

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    label: String,
    placeholder: String,
    @DrawableRes leadingIcon: Int,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.Sentences,
    imeAction: ImeAction = ImeAction.Done,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    MaterialTextField(
        label = {
            Text(
                text = label,
            )
        },
        leadingIcon = {
            Icon(
                painter = leadingIcon.toPainter(),
                contentDescription = null,
            )
        },
        placeholder = {
            Text(
                text = placeholder,
            )
        },
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            capitalization = capitalization,
            imeAction = imeAction,
        ),
        visualTransformation = visualTransformation,
        maxLines = 1,
        modifier = modifier
            .fillMaxWidth(),
    )
}