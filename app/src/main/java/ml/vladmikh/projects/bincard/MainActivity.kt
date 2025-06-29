package ml.vladmikh.projects.bincard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ml.vladmikh.projects.bincard.ui.card.CardScreen
import ml.vladmikh.projects.bincard.ui.card.CardViewModel
import ml.vladmikh.projects.bincard.ui.history.HistoryScreen
import ml.vladmikh.projects.bincard.ui.history.HistoryViewModel
import ml.vladmikh.projects.bincard.ui.navigation.NavBarItems
import ml.vladmikh.projects.bincard.ui.navigation.NavRoutes
import ml.vladmikh.projects.bincard.ui.theme.BincardTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BincardTheme {
                BinCardApp(Modifier)
            }
        }
    }
}

@Composable
fun BinCardApp(
    modifier: Modifier
) {
    val navController = rememberNavController()
    Scaffold(
        content = { padding -> // We have to pass the scaffold inner padding to our content. That's why we use Box.
            Box(modifier = Modifier.padding(padding)) {
                NavigationHost(modifier = modifier, navController = navController)
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                modifier = Modifier
            )
        }
    )

}

@Composable
fun NavigationHost(modifier: Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Card.route,
    ) {
        composable(NavRoutes.Card.route) {
            val viewModel = hiltViewModel<CardViewModel>()
            CardScreen(modifier,viewModel)
        }

        composable(NavRoutes.History.route) {
            val viewModel = hiltViewModel<HistoryViewModel>()
            HistoryScreen(modifier,viewModel)
        }
    }
}

@Composable
fun BottomNavigationBar(
    navController: NavHostController, modifier: Modifier = Modifier
) {

    NavigationBar(
        modifier = modifier,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavBarItems.BarItems.forEach { screen ->

            NavigationBarItem(
                label = {
                    Text(text = screen.title)
                },
                icon = {
                    Icon(imageVector = screen.image, contentDescription = "")
                },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}