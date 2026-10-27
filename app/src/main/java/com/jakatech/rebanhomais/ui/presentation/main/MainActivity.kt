package com.jakatech.rebanhomais.ui.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.jakatech.rebanhomais.ui.presentation.navigation.AppNavigation
import com.jakatech.rebanhomais.ui.ui.theme.RebanhomaisuiTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RebanhomaisuiTheme {
                AppNavigation()
            }
        }
    }
}
