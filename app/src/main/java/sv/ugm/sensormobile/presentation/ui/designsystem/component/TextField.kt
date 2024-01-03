package sv.ugm.sensormobile.presentation.ui.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.presentation.ui.designsystem.icon.SensorMobileIcons
import sv.ugm.sensormobile.presentation.ui.designsystem.theme.SensorMobileTheme
import sv.ugm.sensormobile.presentation.util.load

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    @DrawableRes leadingIcon: Int,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    helper: String? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    capitalization: KeyboardCapitalization = KeyboardCapitalization.None,
    imeAction: ImeAction = ImeAction.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isMultiline: Boolean = false,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocus = interactionSource.collectIsFocusedAsState().value
    
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            capitalization = capitalization,
            imeAction = imeAction,
        ),
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        singleLine = !isMultiline,
        maxLines = 4,
        textStyle = MaterialTheme.typography.labelMedium
            .copy(color = MaterialTheme.colorScheme.onBackground),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            TextFieldContainer(
                value = value,
                leadingIcon = leadingIcon,
                placeholder = placeholder,
                helper = helper,
                innerTextField = innerTextField,
                isFocus = isFocus,
            )
        },
        cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
        modifier = modifier,
    )
}

@Composable
private fun TextFieldContainer(
    modifier: Modifier = Modifier,
    value: String,
    @DrawableRes leadingIcon: Int,
    placeholder: String,
    helper: String? = null,
    innerTextField: @Composable () -> Unit,
    isFocus: Boolean,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
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
                .border(
                    width = 1.dp,
                    color = if (isFocus) MaterialTheme.colorScheme.primary.copy(0.4f)
                    else Color.Transparent,
                    shape = MaterialTheme.shapes.medium,
                )
                .padding(vertical = 12.dp)
                .padding(start = 28.dp, end = 10.dp),
        ) {
            Icon(
                icon = leadingIcon,
            )
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                innerTextField()
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(0.5f),
                        softWrap = false,
                    )
                }
            }
        }
        
        if (helper != null) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = helper,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onBackground.copy(0.5f),
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun TextFieldPreview() {
    SensorMobileTheme {
        TextField(
            value = "",
            leadingIcon = SensorMobileIcons.Username,
            placeholder = R.string.edt_placeholder_username_login.load(),
            helper = "Username is required",
            keyboardType = KeyboardType.Text,
            onValueChange = {},
        )
    }
}