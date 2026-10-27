package com.jakatech.rebanhomais.ui.presentation.navigationBar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jakatech.rebanhomais.ui.presentation.animals.AnimalScreen
import com.jakatech.rebanhomais.ui.presentation.dashboard.DashboardScreen
import com.jakatech.rebanhomais.ui.presentation.navigation.BottomDestination
import com.jakatech.rebanhomais.ui.presentation.profile.ProfileScreen

@Composable
fun NavigationBarScreen(
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val destinations = BottomDestination.entries

    Scaffold(
        modifier = modifier,
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                destinations.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                            navController.navigate(destination.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = destination.icon,
                                contentDescription = destination.contentDescription
                            )
                        },
                        label = { Text(destination.title) }
                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomDestination.HOME.route,
            modifier = Modifier.padding(contentPadding)
        ) {
            composable(BottomDestination.HOME.route) {
                DashboardScreen()
            }
            composable(BottomDestination.ADD.route) {
                AnimalScreen()
            }
            composable(BottomDestination.PROFILE.route) {
                ProfileScreen(onLogoutClicked = onLogout)
            }
        }
    }
}
