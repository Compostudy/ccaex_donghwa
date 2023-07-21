package com.compostudy.android.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

object NavBarItems {
    var barItems = listOf(
        BarItem(
            "Home",
            Icons.Filled.Home,
            "Home"
        ),
        BarItem(
            "Genre",
            Icons.Filled.Favorite,
            "Genre"
        ),
        BarItem(
            "Search",
            Icons.Filled.Search,
            "search"
        ),
        BarItem(
            "Mypage",
            Icons.Filled.Person,
            "mypage"
        )

    )
}

data class BarItem(
    var title: String,
    var image: ImageVector,
    var route: String,
)