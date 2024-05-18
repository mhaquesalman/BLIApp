package com.example.bliapp.navigation

enum class AppScreens {
    HomeScreen,
    DetailScreen;

    companion object {
        fun fromRoute(route: String?) =
            when (route?.substringBefore("/")) {
                HomeScreen.name -> HomeScreen
                DetailScreen.name -> DetailScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized!")
            }
    }
}