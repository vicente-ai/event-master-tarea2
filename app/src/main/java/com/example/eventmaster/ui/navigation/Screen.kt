package com.example.eventmaster.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CategoryEvents : Screen("category_events/{categoryName}") {
        fun createRoute(categoryName: String) = "category_events/$categoryName"
    }
    object EventDetail : Screen("event_detail/{eventId}") {
        fun createRoute(eventId: Int) = "event_detail/$eventId"
    }
    object AddEvent : Screen("add_event")
    object AddCategory : Screen("add_category")
}
