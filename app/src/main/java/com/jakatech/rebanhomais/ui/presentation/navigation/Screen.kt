package com.jakatech.rebanhomais.ui.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object MainContainer : Screen("main_container")
}

enum class BottomDestination(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    HOME("home_dashboard", "Home", Icons.Default.Home, "Home Dashboard"),
    ADD("add_animal", "Add", Icons.Default.AddCircle, "Add Animal"),
    PROFILE("profile", "Profile", Icons.Default.Person, "User Profile")
}
