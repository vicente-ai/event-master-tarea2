package com.example.eventmaster.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Event(
    val name: String,
    val category: String,
    val date: String
)

class EventViewModel : ViewModel() {
    // Lista de categorías reactiva
    val categories = mutableStateListOf("Música", "Deportes", "Tecnología")

    // Lista de eventos reactiva
    val events = mutableStateListOf(
        Event("Evento 1", "Música", "15 Oct 2024"),
        Event("Evento 2", "Música", "22 Nov 2024"),
        Event("Partido Final", "Deportes", "10 Dic 2024"),
        Event("Conferencia IA", "Tecnología", "05 Ene 2025")
    )
    
    fun addCategory(name: String) {
        if (name.isNotBlank() && !categories.contains(name)) {
            categories.add(name)
        }
    }

    fun addEvent(name: String, category: String, date: String) {
        if (name.isNotBlank() && category.isNotBlank() && date.isNotBlank()) {
            events.add(Event(name, category, date))
        }
    }

    fun getEventsByCategory(categoryName: String): List<Event> {
        return events.filter { it.category == categoryName }
    }
}