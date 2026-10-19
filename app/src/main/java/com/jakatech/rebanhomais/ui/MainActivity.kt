package com.jakatech.rebanhomais.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jakatech.rebanhomais.ui.ui.theme.RebanhomaisuiTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RebanhomaisuiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val statusMessage by viewModel.uiState.collectAsState()
                    MainScreen(
                        statusMessage = statusMessage,
                        onRegisterClick = {
                            viewModel.registrarNovoAnimal()
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    statusMessage: String,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = statusMessage,
            modifier = Modifier.padding(16.dp)
        )

        Button(onClick = onRegisterClick) {
            Text("Registrar Novo Animal")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RebanhomaisuiTheme {

    }
}