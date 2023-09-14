package com.example.cardatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cardatabase.core.RouteScreens
import com.example.cardatabase.screens.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = RouteScreens.home.route
            ) {
                mainNavGraph(navController)
            }
        }
    }
}

private fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    composable(route = RouteScreens.home.route) {
        HomeScreen(viewModel = hiltViewModel(),navController)
    }
}
