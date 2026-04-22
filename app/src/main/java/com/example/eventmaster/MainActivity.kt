package com.example.eventmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.eventmaster.ui.screens.AddCategoryScreen
import com.example.eventmaster.ui.screens.AddEventScreen
import com.example.eventmaster.ui.screens.CategoryEventsScreen
import com.example.eventmaster.ui.screens.HomeScreen
import com.example.eventmaster.ui.theme.EventMasterTheme
import com.example.eventmaster.ui.viewmodel.EventViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventMasterTheme {
                EventMasterApp()
            }
        }
    }
}

@Composable
fun EventMasterApp() {
    val navController = rememberNavController()
    val eventViewModel: EventViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                viewModel = eventViewModel,
                onCategoryClick = { categoryName ->
                    navController.navigate("category/$categoryName")
                },
                onAddCategoryClick = {
                    navController.navigate("add_category")
                }
            )
        }
        composable(
            route = "category/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            CategoryEventsScreen(
                categoryName = categoryName,
                viewModel = eventViewModel,
                onBack = { navController.popBackStack() },
                onAddEventClick = { selectedCategory ->
                    navController.navigate("add_event/$selectedCategory")
                }
            )
        }
        composable("add_category") {
            AddCategoryScreen(
                viewModel = eventViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            route = "add_event/{categoryName}",
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            AddEventScreen(
                viewModel = eventViewModel,
                categoryName = categoryName,
                onBack = { navController.popBackStack() }
            )
        }
    }
}