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
import com.example.eventmaster.ui.screens.AddCategoryScreen
import com.example.eventmaster.ui.screens.AddEventScreen
import com.example.eventmaster.ui.screens.EventDetailScreen
import com.example.eventmaster.ui.screens.EventListScreen

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
        startDestination = Screen.EventList.route,
        modifier = modifier
    ) {
        composable(Screen.EventList.route) {
            EventListScreen(
                events = events,
                onEventClick = { event ->
                    navController.navigate(Screen.EventDetail.createRoute(event.id))
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
