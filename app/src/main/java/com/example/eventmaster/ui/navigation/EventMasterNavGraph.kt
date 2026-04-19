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

@Composable
fun EventMasterNavGraph(
    navController: NavHostController,
    categories: List<Category>,
    onEventAdded: (Event) -> Unit,
    onCategoryAdded: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.AddEvent.route, // For testing, go to Add Event
        modifier = modifier
    ) {
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
