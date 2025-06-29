package ml.vladmikh.projects.bincard.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Search

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            title = "Card",
            image = Icons.Filled.Search,
            route = "card"
        ),
        BarItem(
            title = "History",
            image = Icons.AutoMirrored.Filled.List,
            route = "history"
        )
    )
}