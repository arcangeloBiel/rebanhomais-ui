package com.jakatech.rebanhomais.ui.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = GreenPrimaryDark,
    primaryContainer = GreenPrimaryContainerDark,
    background = AppBackgroundDark,
    surface = AppSurfaceDark,
    surfaceVariant = AppSurfaceVariantDark,
    onPrimary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = GreenPrimary,
    primaryContainer = GreenPrimaryContainer,
    onPrimaryContainer = GreenOnPrimaryContainer,
    secondary = SageSecondary,
    secondaryContainer = SageSecondaryContainer,
    tertiary = EmeraldTertiary,
    tertiaryContainer = EmeraldTertiaryContainer,
    background = AppBackgroundLight,
    surface = AppSurfaceLight,
    surfaceVariant = AppSurfaceVariantLight,
    onPrimary = Color.White,
    onBackground = Color(0xFF181C19),
    onSurface = Color(0xFF181C19)
)

@Composable
fun RebanhomaisuiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Set false to enforce app's custom brand colors
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
