package com.compostudy.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.compostudy.android.app.navigation.NavBarItems
import com.compostudy.android.app.navigation.NavRoutes
import com.compostudy.android.app.ui.theme.CcaexTheme
import com.compostudy.android.genre.GenreScreen
import com.compostudy.android.home.HomeScreen
import com.compostudy.android.mypage.MyPageScreen
import com.compostudy.android.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CcaexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BottomNavScreen()
                }
            }
        }
    }
}

@Composable
fun BottomNavScreen() {

    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movie") })
        },
        content = {
            Column(Modifier.padding(it)) {
                BottomNavHost(navController = navController)
            }
        },
        bottomBar = { BottomNavBar(navController = navController) },
    )
}

@Composable
fun BottomNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen()
        }

        composable(NavRoutes.Genre.route) {
            GenreScreen()
        }

        composable(NavRoutes.Search.route) {
            SearchScreen()
        }

        composable(NavRoutes.Mypage.route) {
            MyPageScreen()
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    BottomNavigation {

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.barItems.forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },

                icon = {
                    Icon(
                        imageVector = navItem.image,
                        contentDescription = navItem.title
                    )
                },
                label = { Text(text = navItem.title) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CcaexTheme {

    }
}