package com.menene.automarket.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.menene.automarket.app.presentation.screens.autodetail.AutoDetailScreen
import com.menene.automarket.app.presentation.screens.home.HomeScreen

@Composable
fun MainGraph(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home> {
            HomeScreen(
                navHostController = navController,
            )
        }
        composable<Screen.AutoDetail> { backStackEntry ->
            val auto = backStackEntry.toRoute<Screen.AutoDetail>()
            AutoDetailScreen(
                autoId = auto.autoId,
            )
        }
    }
}