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
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun EventMasterNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    eventViewModel: EventViewModel = viewModel()
) {
    val categories = eventViewModel.categories
    val events = eventViewModel.events

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
            val categoryName = URLDecoder.decode(
                backStackEntry.arguments?.getString("categoryName") ?: "",
                StandardCharsets.UTF_8.toString()
            )
            CategoryEventsScreen(
                categoryName = categoryName,
                events = events.filter { it.categoria == categoryName },
                onBack = { navController.popBackStack() },
                onAddEventClick = {
                    navController.navigate(Screen.AddEvent.createRoute(categoryName))
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

        composable(
            route = Screen.AddEvent.route,
            arguments = listOf(navArgument("categoryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoryName = URLDecoder.decode(
                backStackEntry.arguments?.getString("categoryName") ?: "",
                StandardCharsets.UTF_8.toString()
            )
            AddEventScreen(
                categories = categories,
                preselectedCategory = categoryName,
                onEventAdded = {
                    eventViewModel.addEvent(it)
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.AddCategory.route) {
            AddCategoryScreen(
                onCategoryAdded = {
                    eventViewModel.addCategory(it)
                    navController.popBackStack()
                }
            )
        }
    }
}
