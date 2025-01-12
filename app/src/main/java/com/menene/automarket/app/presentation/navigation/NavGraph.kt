package com.menene.automarket.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.menene.automarket.app.presentation.screens.autodetail.AutoDetailScreen
import com.menene.automarket.app.presentation.screens.AutoViewModel
import com.menene.automarket.app.presentation.screens.home.HomeScreen

@Composable
fun MainGraph(
    navController: NavHostController
) {
    val autoViewModel = hiltViewModel<AutoViewModel>()

    NavHost(
        navController = navController,
        startDestination = Screen.Home
    ) {
        composable<Screen.Home>{
            HomeScreen(
                navHostController = navController,
                autoViewModel = autoViewModel
            )
        }
        composable<Screen.AutoDetail>{ backStackEntry ->
            val auto = backStackEntry.toRoute<Screen.AutoDetail>()
            AutoDetailScreen(
                autoId = auto.autoId,
                autoViewModel = autoViewModel
            )
        }
    }
}