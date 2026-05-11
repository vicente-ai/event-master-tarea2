package com.example.eventmaster.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    object Home : Screen

    @Serializable
    data class CategoryEvents(val categoryName: String) : Screen

    @Serializable
    data class EventDetail(val eventId: Int) : Screen

    @Serializable
    data class AddEvent(val categoryName: String) : Screen

    @Serializable
    object AddCategory : Screen
}
