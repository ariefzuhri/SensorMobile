package sv.ugm.sensormobile.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import sv.ugm.sensormobile.ui.util.CONTAINER_PADDING_DP

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
            text = "Login",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(42.dp))
        TextField(
            label = {
                Text(text = "Email")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Email,
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(text = "Masukkan email")
            },
            value = state.email,
            onValueChange = { email ->
                viewModel.onEvent(
                    LoginEvent.OnEmailChanged(email = email),
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                capitalization = KeyboardCapitalization.None,
                imeAction = ImeAction.Next,
            ),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            label = {
                Text(text = "Kata sandi")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Password,
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(text = "Masukkan kata sandi")
            },
            value = state.password,
            onValueChange = { password ->
                viewModel.onEvent(
                    LoginEvent.OnPasswordChanged(password = password),
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                capitalization = KeyboardCapitalization.None,
                imeAction = ImeAction.Done,
            ),
            visualTransformation = PasswordVisualTransformation(),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                viewModel.onEvent(
                    LoginEvent.Login(
                        email = state.email,
                        password = state.password,
                    ),
                )
            },
            enabled = !state.isLoading,
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Text(text = "Login")
        }
    }
}