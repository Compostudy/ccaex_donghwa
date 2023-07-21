package com.compostudy.android.app.navigation

sealed class NavRoutes(val route: String) {

    object Home : NavRoutes("Home")
    object Genre : NavRoutes("Genre")
    object Search : NavRoutes("Search")
    object Mypage : NavRoutes("Mypage")
}