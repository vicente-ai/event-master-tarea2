package com.example.eventmaster.ui.navigation

import java.net.URLEncoder
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CategoryEvents : Screen("category_events/{categoryName}") {
        fun createRoute(categoryName: String) =
            "category_events/${URLEncoder.encode(categoryName, StandardCharsets.UTF_8.toString())}"
    }
    object EventDetail : Screen("event_detail/{eventId}") {
        fun createRoute(eventId: Int) = "event_detail/$eventId"
    }
    object AddEvent : Screen("add_event/{categoryName}") {
        fun createRoute(categoryName: String) =
            "add_event/${URLEncoder.encode(categoryName, StandardCharsets.UTF_8.toString())}"
    }
    object AddCategory : Screen("add_category")
}
