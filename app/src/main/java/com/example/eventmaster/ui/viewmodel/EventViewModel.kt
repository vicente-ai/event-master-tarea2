package com.example.eventmaster.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.eventmaster.data.model.Category
import com.example.eventmaster.data.model.Event

class EventViewModel : ViewModel() {
    val categories = mutableStateListOf(
        Category(1, "Música", "Eventos musicales"),
        Category(2, "Deportes", "Competencias físicas"),
        Category(3, "Tecnología", "Charlas de IT")
    )

    val events = mutableStateListOf(
        Event(1, "Concierto Rock", "conciertos", "Música", "2024-12-01")
    )

    fun addCategory(category: Category) {
        categories.add(category)
    }

    fun addEvent(event: Event) {
        events.add(event)
    }
}
