package com.example.eventmaster.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.eventmaster.data.model.Category
import com.example.eventmaster.data.model.Event
import com.example.eventmaster.ui.screens.*

@Composable
fun EventMasterNavGraph(
    navController: NavHostController,
    categories: List<Category>,
    events: List<Event>,
    onEventAdded: (Event) -> Unit,
    onCategoryAdded: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                categories = categories,
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
                events = events.filter { it.categoria == categoryName },
                onBack = { navController.popBackStack() },
                onAddEventClick = {
                    navController.navigate(Screen.AddEvent.route)
                }
            )
        }

        composable(
            route = Screen.EventDetail.route,
            arguments = listOf(navArgument("eventId") { type = NavType.IntType })
        ) { backStackEntry ->
            val eventId = backStackEntry.arguments?.getInt("eventId")
            val event = events.find { it.id == eventId }
            EventDetailScreen(event = event)
        }

        composable(Screen.AddEvent.route) {
            AddEventScreen(
                categories = categories,
                onEventAdded = {
                    onEventAdded(it)
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.AddCategory.route) {
            AddCategoryScreen(
                onCategoryAdded = {
                    onCategoryAdded(it)
                    navController.popBackStack()
                }
            )
        }
    }
}
