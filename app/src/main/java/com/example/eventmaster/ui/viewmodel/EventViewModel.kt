package com.example.eventmaster.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class Event(
    val name: String,
    val category: String,
    val type: String,
    val date: String
)

class EventViewModel : ViewModel() {
    // Lista de categorías reactiva
    val categories = mutableStateListOf("Música", "Deportes", "Tecnología")
    val eventTypes = listOf("Concierto", "Conferencia", "Taller")

    // Lista de eventos reactiva
    val events = mutableStateListOf(
        Event("Evento 1", "Música", "Concierto", "15 Oct 2024"),
        Event("Evento 2", "Música", "Concierto", "22 Nov 2024"),
        Event("Partido Final", "Deportes", "Taller", "10 Dic 2024"),
        Event("Conferencia IA", "Tecnología", "Conferencia", "05 Ene 2025")
    )
    
    fun addCategory(name: String) {
        if (name.isNotBlank() && !categories.contains(name)) {
            categories.add(name)
        }
    }

    fun addEvent(name: String, category: String, type: String, date: String) {
        if (name.isNotBlank() && category.isNotBlank() && type.isNotBlank() && date.isNotBlank()) {
            events.add(Event(name, category, type, date))
        }
    }

    fun getEventsByCategory(categoryName: String): List<Event> {
        return events.filter { it.category == categoryName }
    }
}