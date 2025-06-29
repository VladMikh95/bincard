package ml.vladmikh.projects.bincard.ui.navigation

sealed class NavRoutes(val route: String) {
    object Card : NavRoutes("card")
    object History : NavRoutes("history")
}