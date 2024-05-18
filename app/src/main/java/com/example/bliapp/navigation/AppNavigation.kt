package com.example.bliapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.bliapp.screens.DetailScreen
import com.example.bliapp.screens.HomeScreen
import com.example.bliapp.viewmodels.NewsViewModel

@Composable
fun AppNavigation(
    newsViewModel: NewsViewModel
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.HomeScreen.name
    ) {

        // Home Screen
        composable(route = AppScreens.HomeScreen.name) {
            HomeScreen(navController = navController, newsViewModel = newsViewModel)
        }

        // Detail Screen
        val detailScreen = AppScreens.DetailScreen.name
        composable(route = "$detailScreen/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val newsId = backStackEntry.arguments?.getInt("id")
            newsId?.let {
                DetailScreen(
                    navController = navController,
                    newsViewModel = newsViewModel,
                    newsId = newsId
                )
            }
        }
    }

}