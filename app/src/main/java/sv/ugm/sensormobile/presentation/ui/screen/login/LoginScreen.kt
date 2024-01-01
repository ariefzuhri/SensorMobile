package sv.ugm.sensormobile.presentation.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import sv.ugm.sensormobile.R
import sv.ugm.sensormobile.presentation.ui.designsystem.component.Button
import sv.ugm.sensormobile.presentation.ui.designsystem.component.TextField
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
            .padding(CONTAINER_PADDING_DP.dp),
    ) {
        Text(
            text = R.string.txt_title_login.load(),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
        )
        
        Spacer(modifier = Modifier.height(42.dp))
        
        TextField(
            label = R.string.tf_label_email_login.load(),
            leadingIcon = Icons.Rounded.Email,
            placeholder = R.string.tf_placeholder_email_login.load(),
            value = state.email,
            onValueChange = { email ->
                viewModel.onEvent(
                    LoginEvent.OnEmailChanged(email = email),
                )
            },
            keyboardType = KeyboardType.Email,
            capitalization = KeyboardCapitalization.None,
            imeAction = ImeAction.Next,
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        TextField(
            label = R.string.tf_label_password_login.load(),
            leadingIcon = Icons.Rounded.Password,
            placeholder = R.string.tf_placeholder_password_login.load(),
            value = state.password,
            onValueChange = { password ->
                viewModel.onEvent(
                    LoginEvent.OnPasswordChanged(password = password),
                )
            },
            keyboardType = KeyboardType.Password,
            capitalization = KeyboardCapitalization.None,
            visualTransformation = PasswordVisualTransformation(),
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            title = R.string.btn_login_login.load(),
            onClick = {
                viewModel.onEvent(
                    LoginEvent.Login(
                        email = state.email,
                        password = state.password,
                    ),
                )
            },
            enabled = !state.isLoading && state.email.isNotEmpty() && state.password.isNotEmpty(),
        )
    }
}