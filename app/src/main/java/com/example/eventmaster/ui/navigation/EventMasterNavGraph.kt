package com.example.eventmaster.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.eventmaster.ui.screens.*
import com.example.eventmaster.ui.viewmodel.EventViewModel

@Composable
fun EventMasterNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val eventViewModel: EventViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = eventViewModel,
                onCategoryClick = { categoryName ->
                    navController.navigate(Screen.CategoryEvents.createRoute(categoryName))
                },
                onAddCategoryClick = {
                    navController.navigate(Screen.AddCategory.route)
                }
            )
        }
        
        composable(
            route = Screen.CategoryEvents.route,
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString("categoryName") ?: ""
            CategoryEventsScreen(
                categoryName = categoryName,
                viewModel = eventViewModel,
                onBack = { navController.popBackStack() },
                onAddEventClick = { selectedCategory ->
                    navController.navigate(Screen.AddEvent.createRoute(selectedCategory))
                }
            )
        }

        composable(Screen.AddCategory.route) {
            AddCategoryScreen(
                viewModel = eventViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.AddEvent.route,
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