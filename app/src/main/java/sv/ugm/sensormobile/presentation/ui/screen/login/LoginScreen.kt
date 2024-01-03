package sv.ugm.sensormobile.presentation.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.presentation.ui.designsystem.component.Badge
import sv.ugm.sensormobile.presentation.ui.designsystem.component.Icon
import sv.ugm.sensormobile.presentation.ui.designsystem.component.PrimaryButton
import sv.ugm.sensormobile.presentation.ui.designsystem.component.TextField
import sv.ugm.sensormobile.presentation.ui.designsystem.icon.SensorMobileIcons
import sv.ugm.sensormobile.presentation.util.CONTAINER_PADDING_DP
import sv.ugm.sensormobile.presentation.util.asToast
import sv.ugm.sensormobile.presentation.util.load

@Composable
fun LoginScreen(
    restartApp: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),
) {
    val state = loginViewModel.state.value
    
    LoginContent(
        state = state,
        viewModel = loginViewModel,
    )
    
    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            restartApp()
        }
    }
    
    val context = LocalContext.current
    LaunchedEffect(state.failureMessage) {
        state.failureMessage.value.asToast(context)
    }
}

@Composable
private fun LoginContent(
    state: LoginState,
    viewModel: LoginViewModel,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(CONTAINER_PADDING_DP.dp),
    ) {
        HeaderSection(
            state = state,
        )
        Spacer(modifier = Modifier.height(64.dp))
        LoginSection(
            state = state,
            viewModel = viewModel,
        )
        Spacer(modifier = Modifier.height(64.dp))
        Spacer(modifier = Modifier.weight(1f))
        FooterSection()
    }
}

@Composable
private fun HeaderSection(
    state: LoginState,
) {
    Column {
        Spacer(modifier = Modifier.height(72.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                icon = SensorMobileIcons.Logo,
                size = 64.dp,
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = R.string.app_name.load(),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
            )
        }
        Badge(
            text = state.appVersion,
            modifier = Modifier
                .align(Alignment.End),
        )
    }
}

@Composable
private fun LoginSection(
    state: LoginState,
    viewModel: LoginViewModel,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = R.string.txt_title_login.load(),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.size(12.dp))
        Divider(
            thickness = (0.5).dp,
            color = MaterialTheme.colorScheme.outlineVariant.copy(0.5f),
            modifier = Modifier
                .width(56.dp)
        )
    }
    
    Spacer(modifier = Modifier.height(42.dp))
    
    TextField(
        leadingIcon = SensorMobileIcons.Username,
        placeholder = R.string.edt_placeholder_username_login.load(),
        value = state.username,
        onValueChange = { username ->
            viewModel.onEvent(
                LoginEvent.OnUsernameChanged(username = username),
            )
        },
        imeAction = ImeAction.Next,
    )
    
    Spacer(modifier = Modifier.height(20.dp))
    
    TextField(
        leadingIcon = SensorMobileIcons.Password,
        placeholder = R.string.edt_placeholder_password_login.load(),
        value = state.password,
        onValueChange = { password ->
            viewModel.onEvent(
                LoginEvent.OnPasswordChanged(password = password),
            )
        },
        keyboardType = KeyboardType.Password,
        visualTransformation = PasswordVisualTransformation(),
    )
    
    Spacer(modifier = Modifier.height(42.dp))
    
    PrimaryButton(
        title = R.string.btn_log_in_login.load(),
        icon = SensorMobileIcons.Login,
        onClick = {
            viewModel.onEvent(
                LoginEvent.LogIn(
                    username = state.username,
                    password = state.password,
                ),
            )
        },
        isLoading = state.isLoading,
        enabled = state.username.isNotEmpty() && state.password.isNotEmpty(),
        modifier = Modifier
            .fillMaxWidth(),
    )
}

@Composable
private fun FooterSection() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(2.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            icon = SensorMobileIcons.LightSensor,
            tint = MaterialTheme.colorScheme.outlineVariant,
        )
        Icon(
            icon = SensorMobileIcons.SoilMoistureSensor,
            tint = MaterialTheme.colorScheme.outlineVariant,
        )
        Icon(
            icon = SensorMobileIcons.AirQualitySensor,
            tint = MaterialTheme.colorScheme.outlineVariant,
        )
        Icon(
            icon = SensorMobileIcons.RaindropSensor,
            tint = MaterialTheme.colorScheme.outlineVariant,
        )
        Icon(
            icon = SensorMobileIcons.HumiditySensor,
            tint = MaterialTheme.colorScheme.outlineVariant,
        )
        Icon(
            icon = SensorMobileIcons.Temperature1Sensor,
            tint = MaterialTheme.colorScheme.outlineVariant,
        )
        Icon(
            icon = SensorMobileIcons.PressureSensor,
            tint = MaterialTheme.colorScheme.outlineVariant,
        )
        Icon(
            icon = SensorMobileIcons.ApproxAltitudeSensor,
            tint = MaterialTheme.colorScheme.outlineVariant,
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}